package com.taoist.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "INVENTORY")
public class Inventory {

    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @Id
    UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    Book book;

    Date loanDate;

}
