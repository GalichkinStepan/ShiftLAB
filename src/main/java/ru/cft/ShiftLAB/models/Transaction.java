package ru.cft.ShiftLAB.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    public Transaction(Seller seller, int amount, String paymentType)
    {
        this.seller = seller;
        this.amount = amount;
        this.paymentType = paymentType;
        this.transactionDate = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "seller")
    private Seller seller;

    @Column(name = "amount")
    private int amount;

    @Column(name = "paymenttype")
    private String paymentType;

    @Column(name = "transactiondate")
    private LocalDateTime transactionDate;
}
