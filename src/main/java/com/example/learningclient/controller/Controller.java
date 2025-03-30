package com.example.learningclient.controller;

import com.example.learningclient.data.CardEntity;
import com.example.learningclient.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/client/rest")
public class Controller {
    @Autowired
    private CardService cardService;

    @PostMapping(value = "/insert-card")
    public void insertCard(@RequestBody CardEntity card){
        cardService.insertCard(card);
    }

    @PostMapping(value = "/get-card-by-pan")
    public CardEntity getCardByPan(@RequestParam String pan){
        return cardService.getCardByPan(pan);
    }
}
