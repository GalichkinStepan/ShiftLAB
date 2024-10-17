package ru.cft.ShiftLAB.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "seller")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "seller")
    private Seller seller;

    @Column(name = "amount")
    private int amount;

    @Column(name = "paymenttype")
    private int paymentType; //TODO: Доделать тип данных

    @Column(name = "transactiondate")
    private LocalDateTime transactionDate;
}
