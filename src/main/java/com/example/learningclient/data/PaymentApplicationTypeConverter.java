package com.example.learningclient.data;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PaymentApplicationTypeConverter implements AttributeConverter<PaymentApplicationType, Short> {

    @Override
    public Short convertToDatabaseColumn(PaymentApplicationType paymentApplicationType) {
        return paymentApplicationType.getValue();
    }

    @Override
    public PaymentApplicationType convertToEntityAttribute(Short aShort) {
        return PaymentApplicationType.fromValue(aShort);
    }
}
