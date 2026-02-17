package com.lms.libraryManagement.service;


import com.lms.libraryManagement.enums.TransactionType;
import com.lms.libraryManagement.models.Book;
import com.lms.libraryManagement.models.Card;
import com.lms.libraryManagement.models.Transaction;
import com.lms.libraryManagement.repository.BookRepository;
import com.lms.libraryManagement.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookService bookService;
    @Autowired
    CardService cardService;
    @Autowired
    TransactionRepository transactionRepository;

    @Transactional
    public void borrowBook(int studentId , int bookId){

        //find the card
        Card card = cardService.findByStudentId(studentId);

        Optional<Transaction> activeTransaction = transactionRepository.findByIsActiveAndCard_Id(true , card.getId());
        if(activeTransaction.isPresent()){
            throw new RuntimeException("Student has already taken a book can't provide another book");
        }

        Book book = bookService.findBookById(bookId);
        if(!book.isAvaliable()) {
            throw new RuntimeException("Book is not avaliable!");
        }

        //create transaction
        Transaction transaction = new Transaction();
        transaction.setBook(book);
        transaction.setTransactionType(TransactionType.BORROW);
        transaction.setCard(card);
        transaction.setActive(true);
        transaction.setDueDate(LocalDate.now().plusDays(15));

        //save transaction in card
        List<Transaction> transactions = card.getTransactions();
        transactions.add(transaction);
        card.setTransactions(transactions);

        //mark book as unavailable
        book.setAvaliable(false);

        bookRepository.save(book);
        transactionRepository.save(transaction);
    }

    @Transactional
    public void returnBook(int studentId , int bookId){

        Card card = cardService.findByStudentId(studentId);
        Optional<Transaction> activeTransaction = transactionRepository.findByIsActiveAndCard_Id(true , card.getId());

        if(activeTransaction.isEmpty()){
            throw new RuntimeException("There is no any active transactions of this student.");
        }

        //get the book
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()->new RuntimeException("Invalid book id."));

        activeTransaction.get().setActive(false);
        activeTransaction.get().setReturnDate(LocalDate.now());
        activeTransaction.get().setTransactionType(TransactionType.RETURN);

        book.setAvaliable(true);

        transactionRepository.save(activeTransaction.get());
        bookRepository.save(book);
    }

}
