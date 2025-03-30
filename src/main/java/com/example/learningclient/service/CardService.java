package com.example.learningclient.service;

import com.example.learningclient.data.CardEntity;

public interface CardService {
    void insertCard(CardEntity card);
    CardEntity getCardByPan(String pan);
}
