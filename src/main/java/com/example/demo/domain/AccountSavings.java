package com.example.demo.domain;

import java.math.BigDecimal;

import com.example.demo.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountSavings {
    String accountNumber;
    AccountType accountType;
    BigDecimal availableBalance;
}