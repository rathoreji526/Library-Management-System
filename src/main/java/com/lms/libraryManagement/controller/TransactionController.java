package com.lms.libraryManagement.controller;

import com.lms.libraryManagement.responseDTO.ResponseMessage;
import com.lms.libraryManagement.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/borrowBook")
    public ResponseEntity<ResponseMessage> borrowBook(
            @RequestParam int studentId,
            @RequestParam int bookId){
        try{
            transactionService.borrowBook(studentId , bookId);
            return new ResponseEntity<>(new ResponseMessage("Book issued successfully.") , HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/returnBook")
    public ResponseEntity<ResponseMessage> returnBook(
            @RequestParam int studentId,
            @RequestParam int bookId){
        try{
            transactionService.returnBook(studentId , bookId);
            return new ResponseEntity<>(new ResponseMessage("Book returned successfully.") , HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }
    }
}

