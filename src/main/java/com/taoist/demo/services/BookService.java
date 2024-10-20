package com.taoist.demo.services;

import com.taoist.demo.entities.Book;
import com.taoist.demo.entities.User;
import com.taoist.demo.exceptions.NotFoundException;
import com.taoist.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    BookRepository repository;

    public Book getBookById(UUID id) {
        Optional<Book> optionalBook = repository.findById(id);
        return optionalBook.orElseThrow(() -> new NotFoundException("Book not found"));
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }
}
