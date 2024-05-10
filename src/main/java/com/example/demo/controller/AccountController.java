package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.CustomerCreationResponse;
import com.example.demo.CustomerInquiryResponse;
import com.example.demo.domain.CustomerDTO;
import com.example.demo.service.CustomerInquiryService;
import com.example.demo.service.CustomerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {


    
    private final CustomerService customerService;
    private final CustomerInquiryService customerInquiryService;

    @PostMapping()
    public ResponseEntity<CustomerCreationResponse> postMethodName(@RequestBody CustomerDTO customerDTO) {
        log.info("Creating Customer data");
        log.info(customerDTO.getAccountType());

        // Check if customerName is populated
        CustomerCreationResponse customerResponse = customerService.generateCustomer(customerDTO);
        if (customerResponse.transactionStatusCode == 400)
            return ResponseEntity.status(400).body(customerResponse);
        return ResponseEntity.status(customerResponse.transactionStatusCode).body(customerResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerInquiryResponse> getMethodName(@PathVariable Long id) {
        log.info("Finding Customer data given Customer ID");
        CustomerInquiryResponse customerInquiryResponse = customerInquiryService.findByCustomerId(id);
        if (customerInquiryResponse.transactionStatusCode == 401)
            return ResponseEntity.status(401).body(customerInquiryResponse);
        return ResponseEntity.status(customerInquiryResponse.transactionStatusCode).body(customerInquiryResponse);
    }    
}


