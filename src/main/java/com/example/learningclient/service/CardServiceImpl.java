package com.example.learningclient.service;

import com.example.learningclient.data.CardEntity;
import com.example.learningclient.properties.LearningServerProperties;
import com.example.learningclient.provider.LearningServerFeignClient;
import com.example.learningclient.security.SecuredRestTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CardServiceImpl implements CardService{
    private final SecuredRestTemplate securedRestTemplate;
    private final LearningServerProperties learningServerProperties;
    private final LearningServerFeignClient feignClient;

    public CardServiceImpl(SecuredRestTemplate securedRestTemplate, LearningServerProperties learningServerProperties, LearningServerFeignClient feignClient) {
        this.securedRestTemplate = securedRestTemplate;
        this.learningServerProperties = learningServerProperties;
        this.feignClient = feignClient;
    }

    @Override
    public void insertCard(CardEntity card) {
    }

    @Override
    public CardEntity getCardByPan(String pan) {
        CardEntity response;
        try {
            response = feignClient.getCardByPan(pan);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
