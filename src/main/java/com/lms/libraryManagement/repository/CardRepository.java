package com.lms.libraryManagement.repository;

import com.lms.libraryManagement.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    public Card findByStudentId(int studentID);
}
