package com.taoist.demo.util;

import com.taoist.demo.dto.BookResponse;
import com.taoist.demo.dto.InventoryBookResponse;
import com.taoist.demo.dto.InventoryUserResponse;
import com.taoist.demo.dto.UserResponse;
import com.taoist.demo.entities.Book;
import com.taoist.demo.entities.Inventory;
import com.taoist.demo.entities.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapper {

    public UserResponse createUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId().toString());
        userResponse.setRole(user.getRole());
        userResponse.setUsername(user.getUsername());
        List<InventoryBookResponse> inventoryBookResponseList = new ArrayList<>();
        user.getInventories().forEach(i -> inventoryBookResponseList.add(createInventoryBookResponse(i)));
        userResponse.setInventoryBookResponses(inventoryBookResponseList);
        return userResponse;
    }

    public InventoryBookResponse createInventoryBookResponse(Inventory inventory) {
        InventoryBookResponse inventoryBookResponse = new InventoryBookResponse();
        inventoryBookResponse.setId(inventory.getId());
        inventoryBookResponse.setLoanDate(inventory.getLoanDate());
        inventoryBookResponse.setBookResponse(createBookResponse(inventory.getBook()));
        return inventoryBookResponse;
    }

    public BookResponse createBookResponse(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setAuthor(book.getAuthor());
        bookResponse.setImage(book.getImage());
        bookResponse.setId(book.getId());
        bookResponse.setTitle(book.getTitle());
        bookResponse.setInventoryUserResponseList(new ArrayList<>());
        book.getInventories().forEach(i -> {
            bookResponse.getInventoryUserResponseList().add(buildInventoryUserResponse(i));
        });
        return bookResponse;
    }

    public InventoryUserResponse buildInventoryUserResponse(Inventory inventory) {
        InventoryUserResponse inventoryUserResponse = new InventoryUserResponse();
        if (inventory.getUser() != null) {
            inventoryUserResponse.setUser(inventory.getUser().getUsername());
        }
        inventoryUserResponse.setId(inventory.getId());
        inventoryUserResponse.setLoanDate(inventory.getLoanDate());
        return inventoryUserResponse;
    }
}
