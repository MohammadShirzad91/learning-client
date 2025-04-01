package com.example.learningclient.security;

import com.example.learningclient.properties.SecurityProperties;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.net.http.HttpClient;
import java.security.KeyStore;
import java.security.KeyStoreException;

@Component
public class HttpClientBuilder {
    private final SecurityProperties securityProperties;
    private KeyStore trustStore;

    {
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpClientBuilder(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
    @PostConstruct
    public void init() throws Exception {
        try (var trustStoreStream = new FileInputStream(securityProperties.getTrustStore())){
            trustStore.load(trustStoreStream, securityProperties.getTrustStorePassword().toCharArray());
        }
    }

    public HttpClient build() throws Exception{
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(trustStore);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

        return HttpClient.newBuilder()
                .sslContext(sslContext)
                .build();
    }
}
