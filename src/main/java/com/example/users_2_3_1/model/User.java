package com.example.users_2_3_1.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Size(min = 1, max = 50)
    @NotEmpty
    private String name;

    @Column(name = "email")
    @Size(min = 1, max = 100)
    @NotEmpty
    private String email;

    @Column(name = "age")
    @NotEmpty
    private Integer age;
}
