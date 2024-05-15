package com.example.demo.service;

import com.example.demo.CustomerInquiryResponse;

public interface CustomerInquiryService {
    
    /**
     * This method returns customer data given Customer ID. Also returns Account balance
     * of Customer's Accounts
     * @param customerId
     * @return Customer Inquiry Response
     */
    CustomerInquiryResponse findByCustomerId(String customerId);
}