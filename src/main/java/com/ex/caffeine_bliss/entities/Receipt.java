package com.ex.caffeine_bliss.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.UUID;

@Table(name = "receipts")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_receipts_order"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Order order;

    @Column(length = 100, nullable = false)
    private String email;

    @CreationTimestamp
    @Column(name = "sent_at")
    private Date sentAt;

    public Receipt(Order order, String email) {
        this.order = order;
        this.email = email;
    }
}
