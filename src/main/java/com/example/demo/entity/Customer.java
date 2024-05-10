package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Customer {
    @GeneratedValue
    @Id
    private Long customerNumber;
    
    
    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String customerMobile;

    @Column(nullable = false)
    private String customerEmail;

    @Column(nullable = false)
    private String address1;

    private String address2;

    public Customer(String customerName, String customerMobile, String customerEmail, String address1, String address2) {
        this.customerName = customerName;
        this.customerMobile = customerMobile;
        this.customerEmail = customerEmail;

        this.address1 = address1;
        this.address2 = address2;
    }
}
