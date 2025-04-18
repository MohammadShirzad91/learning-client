package com.example.learningclient.service;

import com.example.learningclient.data.CardEntity;
import com.example.learningclient.provider.LearningServerFeignClient;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService{
    private final LearningServerFeignClient feignClient;

    public CardServiceImpl(LearningServerFeignClient feignClient) {
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
