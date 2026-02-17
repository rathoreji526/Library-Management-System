package com.lms.libraryManagement.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "title" , nullable = false)
    private String title;
    @Column(name = "publisherName" , nullable = false)
    private String publisherName;
    @Column(name = "publishedYear" , nullable = false)
    private LocalDate publishedYear;
    @Column(name = "language" , nullable = false)
    private String language;
    @Column(name = "pages" , nullable = false)
    private int pages;
    @Column(name = "avaliable" , nullable = false)
    private boolean isAvaliable;

    @ManyToOne
    @JoinColumn
    private Card card;

    @OneToMany(mappedBy = "book")
    List<Transaction> transactionList;

}
