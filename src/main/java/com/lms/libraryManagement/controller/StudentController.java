package com.lms.libraryManagement.controller;

import com.lms.libraryManagement.requestDTO.StudentRequestDTO;
import com.lms.libraryManagement.enums.Gender;
import com.lms.libraryManagement.models.Student;
import com.lms.libraryManagement.responseDTO.ResponseMessage;
import com.lms.libraryManagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/apis")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createStudent(@RequestBody StudentRequestDTO studentDTO){
        try{
            String response = studentService.createStudent(studentDTO);
            return new ResponseEntity<>(new ResponseMessage(response) , HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        try{
            Student student = studentService.findById(id);
            return new ResponseEntity<>(student , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/findByMobile")
    public ResponseEntity<?> findByMobile(@RequestParam String mobile){
        try{
            Student student = studentService.findByMobile(mobile);
            return new ResponseEntity<>(student , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/findByGender")
    public ResponseEntity<?> findByGender(@RequestParam Gender gender){
        try{
            List<Student> students = studentService.findByGender(gender);
            return new ResponseEntity<>(students , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/updateStudent")
    public ResponseEntity<?> updateStudent(@PathVariable int id,
                                        @RequestBody StudentRequestDTO studentDTO){
        try{
            studentService.updateStudentByPut(id , studentDTO);
            return new  ResponseEntity<>("Student updated successfully" , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessage> deleteStudent(@PathVariable int id){
        try{
            studentService.deleteStudent(id);
            return new ResponseEntity<>(new ResponseMessage("Student deleted successfully"), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }
    }

}
