package com.lms.libraryManagement.models;

import com.lms.libraryManagement.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "transaction_date")
    @CreationTimestamp
    private Date transactionDate;

    @Column(name = "due_date" , nullable = false)
    private LocalDate dueDate;
    private LocalDate returnDate;

    @Column(name = "transaction_time" , nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;
    private boolean isActive;


    @ManyToOne
    @JoinColumn
    private Card card;

    @ManyToOne
    @JoinColumn
    private Book book;
}
