package com.lms.libraryManagement.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lms.libraryManagement.enums.CardStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "card_status" , nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    @Column(name = "expiry_date" , nullable = false)
    private LocalDate expiryDate;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDate createdDate;

    @Column(name = "updated_date")
    @CreationTimestamp
    private LocalDate updatedDate;

    //card and student relation
    @OneToOne
    @JoinColumn
    @JsonBackReference
    private Student student;

    //card and transaction relation
    @OneToMany(mappedBy = "card")
    List<Transaction> transactions;

    //card & book relation
    @OneToMany(mappedBy = "card")
    private List<Book> bookList;
}
