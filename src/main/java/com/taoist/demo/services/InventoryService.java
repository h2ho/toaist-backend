package com.taoist.demo.services;

import com.taoist.demo.entities.Inventory;
import com.taoist.demo.entities.User;
import com.taoist.demo.exceptions.BookAlreadyBorrowedException;
import com.taoist.demo.exceptions.NotFoundException;
import com.taoist.demo.exceptions.BookReturnConflictException;
import com.taoist.demo.repositories.InventoryRepository;
import com.taoist.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    UserRepository userRepository;

    public void borrowBook(UUID userId, UUID inventoryId) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(inventoryId);

        Inventory found = optionalInventory.orElseThrow(() -> new NotFoundException("Book not found"));
        if (found.getUser() != null) {
            throw new BookAlreadyBorrowedException("Book already borrowed");
        }
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElseThrow(() -> new NotFoundException("User not found"));
        user.getInventories().add(found);
        found.setUser(user);
    }

    public void returnBook(UUID userId, UUID inventoryId) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(inventoryId);
        Inventory found = optionalInventory.orElseThrow(() -> new NotFoundException("Book not found"));

        if (found.getUser() != null) {
            if (found.getUser().getId() != userId) {
                throw new BookReturnConflictException("not owner");
            }
            found.setUser(null);
        }
    }
}
