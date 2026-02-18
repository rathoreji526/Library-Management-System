package com.lms.libraryManagement.repository;

import com.lms.libraryManagement.enums.Gender;
import com.lms.libraryManagement.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student , Integer> {
}
