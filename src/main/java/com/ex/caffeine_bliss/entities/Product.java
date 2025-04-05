package com.ex.caffeine_bliss.entities;

import com.ex.caffeine_bliss.entities.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Table(name = "products")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Check(constraints = "stock_quantity >= 0")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(updatable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(updatable = false)
    private ProductCategory category;

    @Column(updatable = false)
    private double price;

    @Column(name = "stock_quantity", updatable = false)
    private int stockQuantity;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
}
