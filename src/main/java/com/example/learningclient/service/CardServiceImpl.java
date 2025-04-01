package com.example.learningclient.service;

import com.example.learningclient.data.CardEntity;
import com.example.learningclient.properties.LearningServerProperties;
import com.example.learningclient.security.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class CardServiceImpl implements CardService{
    private final HttpClientBuilder httpClientBuilder;
    private final LearningServerProperties learningServerProperties;

    public CardServiceImpl(HttpClientBuilder httpClientBuilder, LearningServerProperties learningServerProperties) {
        this.httpClientBuilder = httpClientBuilder;
        this.learningServerProperties = learningServerProperties;
    }

    @Override
    public void insertCard(CardEntity card) {
    }

    @Override
    public CardEntity getCardByPan(String pan) {
        String uriString = UriComponentsBuilder.fromUriString("/get-card-by-pan").queryParam("pan", pan).toUriString();
        HttpEntity<Void> entity = new HttpEntity<>(null);
        HttpClient httpClient;
        try {
            httpClient= httpClientBuilder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        HttpResponse.BodyHandler<CardEntity> bodyHandler = null;
        HttpResponse<CardEntity> response = null;
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(learningServerProperties.getBaseUrl() + "/get-card-by-pan")).GET().build();
        try {
            response = httpClientBuilder.build().send(httpRequest, bodyHandler);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response.body();
    }
}
