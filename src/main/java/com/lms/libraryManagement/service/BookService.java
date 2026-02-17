package com.lms.libraryManagement.service;

import com.lms.libraryManagement.models.Book;
import com.lms.libraryManagement.models.Transaction;
import com.lms.libraryManagement.repository.BookRepository;
import com.lms.libraryManagement.requestDTO.BookRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    //create book
    public void addBook(BookRequestDTO dto){
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setPublishedYear(dto.getPublishedYear());
        book.setPages(dto.getPages());
        book.setLanguage(dto.getLanguage());
        book.setPublisherName(dto.getPublisherName());
        book.setAvaliable(true);
        book.setTransactionList(new ArrayList<Transaction>());
        bookRepository.save(book);
    }

    //update book
    public void updateBook(int bookId , BookRequestDTO dto){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()->new RuntimeException("Book not found"));
        book.setTitle(dto.getTitle());
        book.setPublishedYear(dto.getPublishedYear());
        book.setPages(dto.getPages());
        book.setLanguage(dto.getLanguage());
        book.setPublisherName(dto.getPublisherName());
        book.setAvaliable(true);
        book.setTransactionList(new ArrayList<Transaction>());
        bookRepository.save(book);
    }
    //delete book
    public void deleteBook(int bookId){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()->new RuntimeException("Book not found"));
        bookRepository.delete(book);
    }
    //find book
    public Book findBookById(int bookId){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()->new RuntimeException("Book with id: "+bookId+" not found"));
        return book;
    }
}
