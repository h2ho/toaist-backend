package com.taoist.demo.dto;

import com.taoist.demo.entities.Inventory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class BookResponse {
    UUID id;
    String title;
    String image;
    String author;
    List<InventoryUserResponse> inventoryUserResponseList;
}
