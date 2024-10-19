package com.taoist.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    @OneToMany
    List<Inventory> inventories;

}
