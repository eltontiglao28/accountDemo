package com.example.demo.domain;


import lombok.Value;


@Value
public class CustomerDTO {
    private String customerName;

    private String customerMobile;

    private String customerEmail;

    private String address1;

    private String address2;

    private String accountType;
}
