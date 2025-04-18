package com.example.learningclient.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "client")
@Data
@Component
public class SecurityProperties {
    private String trustStore;
    private String trustStorePassword;
}
