package com.lms.libraryManagement.repository;

import com.lms.libraryManagement.models.Student;
import com.lms.libraryManagement.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    public Optional<Transaction> findByIsActiveAndCard_Id(boolean isActive , int cardId);
}
