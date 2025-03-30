package com.example.learningclient.service;

import com.example.learningclient.data.CardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CardServiceImpl implements CardService{
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void insertCard(CardEntity card) {
    }

    @Override
    public CardEntity getCardByPan(String pan) {
        String uriString = UriComponentsBuilder.fromUriString("http://localhost:8080/server/rest/get-card-by-pan").queryParam("pan", pan).toUriString();
        HttpEntity<Void> entity = new HttpEntity<>(null);
        ResponseEntity<CardEntity> responseEntity = restTemplate.exchange(uriString, HttpMethod.GET, entity, CardEntity.class);
        return responseEntity.getBody();
    }
}
