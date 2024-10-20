package com.taoist.demo.controllers;

import com.taoist.demo.dto.BookResponse;
import com.taoist.demo.dto.BorrowRequest;
import com.taoist.demo.entities.Book;
import com.taoist.demo.exceptions.NotFoundException;
import com.taoist.demo.services.BookService;
import com.taoist.demo.services.InventoryService;
import com.taoist.demo.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    InventoryService inventoryService;

    @Autowired
    Mapper mapper;

    @GetMapping
    public ResponseEntity<List<BookResponse>> getBooks() {
        List<BookResponse> bookResponses = new ArrayList<>();
        bookService.getAllBooks().forEach(b -> bookResponses.add(mapper.createBookResponse(b)));
        return ResponseEntity.ok(bookResponses); // Return 200 OK with the list of books
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable UUID id) {
        Book found = bookService.getBookById(id);
        return ResponseEntity.ok(mapper.createBookResponse(found));
    }

    @PutMapping("/borrow")
    public ResponseEntity<Void> borrowBook(@RequestBody BorrowRequest borrowRequest) {
        try {
            inventoryService.borrowBook(borrowRequest.getUserId(), borrowRequest.getInventoryId());
            return ResponseEntity.status(HttpStatus.ACCEPTED).build(); // 202 Accepted
        }
        catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data", e); // 400 Bad Request
        }
    }

    @PutMapping("/return")
    public ResponseEntity<Void> returnBook(@RequestBody BorrowRequest borrowRequest) {
        try {
            inventoryService.returnBook(borrowRequest.getUserId(), borrowRequest.getInventoryId());
            return ResponseEntity.status(HttpStatus.ACCEPTED).build(); // 202 Accepted
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found", e); // 404 Not Found
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data", e); // 400 Bad Request
        }
    }
}
