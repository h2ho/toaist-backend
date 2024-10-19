package com.taoist.demo.services;

import com.taoist.demo.entities.Book;
import com.taoist.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    BookRepository repository;

    public Book getBookById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }
}
