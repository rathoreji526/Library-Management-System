package com.lms.libraryManagement.service;

import com.lms.libraryManagement.enums.CardStatus;
import com.lms.libraryManagement.models.Card;
import com.lms.libraryManagement.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;

    public void deactivateCard(int id){
        Card card =  cardRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Card not found"));
        card.setCardStatus(CardStatus.INACTIVE);
        card.setUpdatedDate(LocalDate.now());
        cardRepository.save(card);
    }
    public Card findByStudentId(int studentId){
        return cardRepository.findByStudentId(studentId);
    }
}
