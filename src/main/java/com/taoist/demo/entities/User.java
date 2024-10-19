package com.taoist.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "APP_USER")
public class User {

    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @Id
    UUID id;
    String username;
    String password;
    String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Inventory> inventories;

}
