package com.example.learningclient.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CARD")
@Getter
@Setter
public class CardEntity {
    @Id
    @Column(name = "PAN")
    private String pan;
    @Column(name = "CIF")
    private String cif;
    @Column(name = "PAYAPPNO")
    private String paymentApplicationNumber;
    @Column(name = "PAYAPPTYP")
    private PaymentApplicationType PaymentApplicationType;
}
