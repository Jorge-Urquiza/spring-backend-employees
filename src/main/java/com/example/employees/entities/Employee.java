package com.example.employees.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "empleados")
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name ="nombre")
    private String name;

    @Column(name ="apellido")
    private String lastName;

    @Column(name ="correo", unique = true)
    private String email;

    @Column(name ="estado") // 1 TRUE, 0 FALSE
    private boolean isActive = true;

    @Column(name = "created_at", nullable = true, updatable = true)
    @CreationTimestamp
    private LocalDateTime createdAt;

}
