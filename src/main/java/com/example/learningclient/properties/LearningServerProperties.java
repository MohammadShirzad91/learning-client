package com.example.learningclient.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "learning.server")
public class LearningServerProperties {
    private String baseUrl;
}
