package com.ex.caffeine_bliss.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Table(name = "customers")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "customer_id")
    private UUID id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(unique = true, length = 15, nullable = false)
    private String mobileNo;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;
}
