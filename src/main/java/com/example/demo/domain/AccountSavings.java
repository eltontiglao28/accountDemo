package com.example.demo.domain;

import java.math.BigDecimal;

import com.example.demo.AccountType;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class AccountSavings {
    Long accountNumber;
    AccountType accountType;
    BigDecimal availableBalance;
}