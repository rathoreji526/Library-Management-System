package com.lms.libraryManagement.controller;

import com.lms.libraryManagement.requestDTO.BookRequestDTO;
import com.lms.libraryManagement.responseDTO.ResponseMessage;
import com.lms.libraryManagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> addBook(@RequestBody BookRequestDTO bookRequestDTO){
        try {
            bookService.addBook(bookRequestDTO);
            return new ResponseEntity<>(new ResponseMessage("Book added successfully.") , HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseMessage> updateBook(
            @RequestParam int id,
            @RequestBody BookRequestDTO bookRequestDTO){
        try {
            bookService.updateBook(id , bookRequestDTO);
            return new ResponseEntity<>(new ResponseMessage("Book updated successfully.") , HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }
    }

}
