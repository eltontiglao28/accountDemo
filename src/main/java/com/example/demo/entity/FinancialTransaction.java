package com.example.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter

public class FinancialTransaction {
    
    @GeneratedValue
    @Id
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;

    @Column(nullable =  false)
    @UpdateTimestamp
    private LocalDateTime transactionDateTime;

    @Column(nullable =  false)
    private BigDecimal amount;

    public FinancialTransaction(Account account, BigDecimal amount) {
        this.account = account;
        this.amount = amount;

    }

}
