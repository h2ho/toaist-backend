package com.taoist.demo.dto;

import com.taoist.demo.entities.Inventory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse {

    String id;
    String username;
    String role;
    List<InventoryBookResponse> inventoryBookResponses;

}
