package com.example.learningclient.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "client")
public class SecurityProperties {
    private String trustStore;
    private String trustStorePassword;
}
