package com.lms.libraryManagement.controller;

import com.lms.libraryManagement.models.Card;
import com.lms.libraryManagement.responseDTO.ResponseMessage;
import com.lms.libraryManagement.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card/apis")
public class CardController {

    @Autowired
    CardService cardService;

    @GetMapping("/findByStudentId")
    public ResponseEntity<?> findByStudentId(@RequestParam int studentId){
        try {
            Card card = cardService.findByStudentId(studentId);
            return new ResponseEntity<>(card, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()) , HttpStatus.OK);
        }
    }
}
