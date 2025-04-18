package com.example.learningclient.config;

import com.example.learningclient.properties.SecurityProperties;
import feign.Client;
import feign.Feign;
import feign.hc5.ApacheHttp5Client;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Configuration
public class FeignConfig {
    @Autowired
    private SecurityProperties securityProperties;

    @Primary
    @Bean
    public Client feignClient() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {

        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
                        new File(securityProperties.getTrustStore()),
                        securityProperties.getTrustStorePassword().toCharArray())
                .build();
        // Create SSLConnectionSocketFactory
        var sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
                .setSslContext(sslContext)
                .build();

        // Create PoolingHttpClientConnectionManager with SSL support
        PoolingHttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(sslSocketFactory)
                .build();

        // Build Apache HttpClient
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();

        // Create Feign client
        return new ApacheHttp5Client(httpClient);
    }
    @Bean
    public Feign.Builder feignBuilder(Client feignClient) {
        return Feign.builder().client(feignClient);
    }
}
