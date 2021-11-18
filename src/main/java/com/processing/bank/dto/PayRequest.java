package com.processing.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class PayRequest {
    String cardNumber;
    String receiverCardNumber;
    BigDecimal amount;
}
