package com.example.demo.service;

import com.example.demo.CustomerCreationResponse;
import com.example.demo.domain.CustomerDTO;

public interface CustomerService {
    
    CustomerCreationResponse generateCustomer(CustomerDTO accountDTO);
}
