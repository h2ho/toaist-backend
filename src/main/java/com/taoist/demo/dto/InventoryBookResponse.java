package com.taoist.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class InventoryBookResponse {
    UUID id;
    Date loanDate;
    BookResponse bookResponse;
}
