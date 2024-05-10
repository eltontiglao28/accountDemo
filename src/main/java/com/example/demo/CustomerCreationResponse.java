package com.example.demo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CustomerCreationResponse {
    public Long customerNumber;
    public int transactionStatusCode;
    public String transactionStatusDescription;
}