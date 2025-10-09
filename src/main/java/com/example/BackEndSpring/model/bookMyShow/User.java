package com.example.BackEndSpring.model.bookMyShow;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    private String phone;

    private String name;
    private String email;
}
