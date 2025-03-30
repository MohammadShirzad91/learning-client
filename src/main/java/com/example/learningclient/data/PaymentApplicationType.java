package com.example.learningclient.data;

public enum PaymentApplicationType {
    DEBIT(((short) 1)),
    GIFT((short) 2);

    private short value;

    PaymentApplicationType(short value) {
        this.value = value;
    }

    public static PaymentApplicationType fromValue(short value){
        var values = PaymentApplicationType.values();
        for (PaymentApplicationType paymentApplicationType : values) {
            if (paymentApplicationType.value == value)
                return paymentApplicationType;
        }
        throw new RuntimeException();
    }

    public short getValue() {
        return value;
    }
}
