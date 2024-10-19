package com.taoist.demo.services;

import com.taoist.demo.entities.Inventory;
import com.taoist.demo.entities.User;
import com.taoist.demo.exceptions.BookAlreadyBorrowedException;
import com.taoist.demo.exceptions.NotFoundException;
import com.taoist.demo.exceptions.BookConflictException;
import com.taoist.demo.repositories.InventoryRepository;
import com.taoist.demo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void borrowBook(UUID userId, UUID inventoryId) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(inventoryId);

        Inventory found = optionalInventory.orElseThrow(() -> new NotFoundException("Book not found"));
        if (found.getUser() != null) {
            throw new BookConflictException("Book already borrowed");
        }
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElseThrow(() -> new NotFoundException("User not found"));
        user.getInventories().add(found);
        found.setUser(user);
        found.setLoanDate(new Date());
        inventoryRepository.save(found);
    }

    @Transactional
    public void returnBook(UUID userId, UUID inventoryId) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(inventoryId);
        Inventory found = optionalInventory.orElseThrow(() -> new NotFoundException("Book not found"));

        if (found.getUser() != null) {
            if (userId == null) {
                throw new BookConflictException("not owner");
            }
            else if (!userId.equals(found.getUser().getId())) {
                throw new BookConflictException("not owner");
            }
            found.setUser(null);
            found.setLoanDate(null);
            inventoryRepository.save(found);
        }
    }
}
