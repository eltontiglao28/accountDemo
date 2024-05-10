package com.example.demo;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.domain.AccountSavings;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Setter
@ToString
@NoArgsConstructor
public class CustomerInquiryResponse {


    public Long customerNumber;
    public String customerName;
    public String customerMobile;
    public String customerEmail;
    public String address1;
    public String address2;

    public List<AccountSavings> savings;
    public int transactionStatusCode;
    public String transactionStatusDescription;

    public CustomerInquiryResponse(int transactionStatusCode, String transactionStatusDescription) {
        this.transactionStatusCode = transactionStatusCode;
        this.transactionStatusDescription = transactionStatusDescription;
    }
    
}
