package com.example.learningclient.service;

import com.example.learningclient.data.CardEntity;
import com.example.learningclient.properties.LearningServerProperties;
import com.example.learningclient.security.SecuredRestTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CardServiceImpl implements CardService{
    private final SecuredRestTemplate securedRestTemplate;
    private final LearningServerProperties learningServerProperties;

    public CardServiceImpl(SecuredRestTemplate securedRestTemplate, LearningServerProperties learningServerProperties) {
        this.securedRestTemplate = securedRestTemplate;
        this.learningServerProperties = learningServerProperties;
    }

    @Override
    public void insertCard(CardEntity card) {
    }

    @Override
    public CardEntity getCardByPan(String pan) {
        CardEntity response;
        Map<String, Object> input = new HashMap<>();
        input.put("pan", pan);
        try {
            response = securedRestTemplate.build().getForObject(learningServerProperties.getBaseUrl() + "/get-card-by-pan", CardEntity.class, input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
