package com.example.learningclient.data;

import lombok.Data;

@Data
public class CardEntity {
    private String pan;
    private String cif;
    private String paymentApplicationNumber;
    private PaymentApplicationType PaymentApplicationType;
}
