package com.taoist.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class InventoryUserResponse {
    UUID id;
    Date loanDate;
    String user;
}
