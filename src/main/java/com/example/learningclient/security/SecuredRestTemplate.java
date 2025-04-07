package com.example.learningclient.security;

import com.example.learningclient.properties.SecurityProperties;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.nio.file.Path;
import java.security.KeyStore;
import java.security.KeyStoreException;

@Component
@Primary
public class SecuredRestTemplate {
    private final SecurityProperties securityProperties;
    private KeyStore trustStore;

    {
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        }
    }

    public SecuredRestTemplate(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public RestTemplate build() throws Exception{
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(Path.of(securityProperties.getTrustStore()), securityProperties.getTrustStorePassword().toCharArray()).build();
        SSLConnectionSocketFactory sslConFactory = new SSLConnectionSocketFactory(sslContext);
        HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(sslConFactory)
                .build();
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(requestFactory);
    }
}
