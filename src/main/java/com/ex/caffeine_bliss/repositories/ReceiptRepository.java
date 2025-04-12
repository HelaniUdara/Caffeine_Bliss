package com.ex.caffeine_bliss.repositories;

import com.ex.caffeine_bliss.DTOs.quesryInterfaces.ReceiptDetailInterface;
import com.ex.caffeine_bliss.entities.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface ReceiptRepository extends JpaRepository<Receipt, UUID> {
    boolean existsByOrder_Id(UUID orderId);

    @Query(value = """
        SELECT 
            BIN_TO_UUID(r.id) AS receiptId, 
            BIN_TO_UUID(o.id) AS orderId,
            r.email AS customerEmail,
            r.sent_at AS sentAt
        FROM receipts r
        JOIN orders o ON o.id = r.order_id
        WHERE o.id = ?1
        """, nativeQuery = true)
    ReceiptDetailInterface findByOrderId(UUID orderId);

    @Query(value = """
        SELECT 
            BIN_TO_UUID(r.id) AS receiptId, 
            BIN_TO_UUID(o.id) AS orderId,
            r.email AS customerEmail,
            r.sent_at AS sentAt
        FROM receipts r
        JOIN orders o ON o.id = r.order_id
        WHERE r.id = ?1
        """, nativeQuery = true)
    ReceiptDetailInterface findByReceiptId(UUID id);
}
