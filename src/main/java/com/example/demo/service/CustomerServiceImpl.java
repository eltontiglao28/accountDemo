package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.CustomerCreationResponse;
import com.example.demo.AccountType;
import com.example.demo.domain.CustomerDTO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private static final int NOT_FOUND_STATUS_CODE = 400;
    private static final int CREATED_STATUS_CODE = 201;
    
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Override
    public CustomerCreationResponse generateCustomer(CustomerDTO customerDTO) {
        //Validations
        // Check if customerName is popoulated
        if (customerDTO.getCustomerName() == null)
            return new CustomerCreationResponse(null, 400, "Customer Name is a required field.");

        // Check if customerMobile is popoulated
        if (customerDTO.getCustomerMobile() == null)
            return new CustomerCreationResponse(null, 400, "Customer Mobile is a required field.");

        // Check if customerEmail is popoulated
        if (customerDTO.getCustomerEmail() == null)
            return new CustomerCreationResponse(null, 400, "Customer Email is a required field.");

        // Check if address1 is popoulated
        if (customerDTO.getAddress1() == null)
            return new CustomerCreationResponse(null, 400, "Address1 is a required field.");

        // Check if accountType is popoulated
        if (customerDTO.getAccountType() == null)
            return new CustomerCreationResponse(null, 400, "Account Type is a required field.");

        if ( !(customerDTO.getAccountType().equals("C") || customerDTO.getAccountType().equals("S")) )
            return new CustomerCreationResponse(null, NOT_FOUND_STATUS_CODE, "Account Type value is invalid");

        AccountType accountType = AccountType.valueOf(customerDTO.getAccountType());
        Customer customer = customerRepository.save(new Customer(
            customerDTO.getCustomerName(),
            customerDTO.getCustomerMobile(),
            customerDTO.getCustomerEmail(),
            customerDTO.getAddress1(),
            customerDTO.getAddress2()
        ));

        accountRepository.save(new Account (
            customer,
            accountType
        ));
        return new CustomerCreationResponse(customer.getCustomerNumber(), CREATED_STATUS_CODE, "Customer Account created");
    
    }
}
