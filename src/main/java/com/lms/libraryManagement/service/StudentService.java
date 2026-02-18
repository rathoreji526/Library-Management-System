package com.lms.libraryManagement.service;

import com.lms.libraryManagement.requestDTO.StudentRequestDTO;
import com.lms.libraryManagement.enums.CardStatus;
import com.lms.libraryManagement.enums.Gender;
import com.lms.libraryManagement.models.Card;
import com.lms.libraryManagement.models.Student;
import com.lms.libraryManagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CardService cardService;
    //add student => when we add student we have to issue a card for the student and mark card active

    public String createStudent(StudentRequestDTO studentDTO){
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setMobile(studentDTO.getMobile());
        student.setDob(studentDTO.getDob());
        student.setAddress(studentDTO.getAddress());
        student.setDept(studentDTO.getDept());
        student.setGender(studentDTO.getGender());
        student.setSem(studentDTO.getSem());

        Card card = new Card();
        card.setStudent(student);
        card.setCardStatus(CardStatus.ACTIVE);
        card.setExpiryDate(LocalDate.now().plusYears(4));
        student.setCard(card);

        studentRepository.save(student);
        return "Student saved successfully";
    }

    // find all students
    public List<Student> findAllStudents(){
        List<Student> students = studentRepository.findAll();
        return students;
    }

    //find by id(by default)
    public Student findById(int id){
        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Student not found"));
        return student;
    }

    //update put
    public void updateStudentByPut(int id , StudentRequestDTO dto){
        Student student = studentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Student not found."));
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setMobile(dto.getMobile());
        student.setDob(dto.getDob());
        student.setAddress(dto.getAddress());
        student.setDept(dto.getDept());
        student.setGender(dto.getGender());
        student.setSem(dto.getSem());

        studentRepository.save(student);
    }

    //fetch student deactivate card and delete student.
    public void deleteStudent(int id){
        Student student = studentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Student not found."));
        cardService.deactivateCard(student.getCard().getId());
        studentRepository.delete(student);
    }

}
