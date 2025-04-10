package com.ex.caffeine_bliss.entities;

import com.ex.caffeine_bliss.entities.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Table(name = "orders")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false, foreignKey = @ForeignKey(name = "fk_orders_customer"))
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cashier_id", nullable = false, foreignKey = @ForeignKey(name = "fk_orders_cashier"))
    private User cashier;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "order")
    private Set<OrderItems> orderItems;

}
