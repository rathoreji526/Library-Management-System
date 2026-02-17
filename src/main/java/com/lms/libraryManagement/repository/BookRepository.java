package com.lms.libraryManagement.repository;

import com.lms.libraryManagement.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book , Integer> {
}
