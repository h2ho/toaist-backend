package com.taoist.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BorrowRequest {
    UUID userId;
    UUID inventoryId;
}
