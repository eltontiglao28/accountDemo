package com.example.demo.service;

import com.example.demo.CustomerCreationResponse;
import com.example.demo.domain.CustomerDTO;

public interface CustomerService {
    
    /**
     * This method checks Customer Infmormation and if valid, creates new Customer 
     * @param accountDTO
     */
    CustomerCreationResponse generateCustomer(CustomerDTO accountDTO);
}
