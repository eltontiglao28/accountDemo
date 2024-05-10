package com.example.demo.service;

import com.example.demo.CustomerInquiryResponse;

public interface CustomerInquiryService {
    
    CustomerInquiryResponse findByCustomerId(Long customerId);
}
