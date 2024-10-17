package ru.cft.ShiftLAB.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "seller")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Seller {

    public Seller(String name, String contactInfo){
        this.name = name;
        this.contactInfo = contactInfo;
        this.registrationDate = LocalDateTime.now();
    }

    public void update(String name, String contactInfo){
        this.name = name;
        this.contactInfo = contactInfo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "contactinfo")
    private String contactInfo;

    @Column(name = "registrationdate")
    private LocalDateTime registrationDate;
}
