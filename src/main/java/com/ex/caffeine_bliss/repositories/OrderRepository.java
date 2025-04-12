package com.ex.caffeine_bliss.repositories;

import com.ex.caffeine_bliss.DTOs.quesryInterfaces.OrderDetailInterface;
import com.ex.caffeine_bliss.DTOs.quesryInterfaces.OrderItemDetailInterface;
import com.ex.caffeine_bliss.DTOs.quesryInterfaces.OrderSummaryInterface;
import com.ex.caffeine_bliss.entities.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query(value = """
        SELECT 
            BIN_TO_UUID(o.id) AS id, 
            o.created_at AS createdAt, 
            o.payment_method AS paymentMethod,
            o.total_price AS totalPrice,
            c.name AS customerName,
            u.first_name AS cashierName
        FROM orders o
        JOIN customers c ON o.customer_id = c.customer_id
        JOIN users u ON o.cashier_id = u.user_id
        WHERE o.customer_id = ?1
        """, nativeQuery = true)
    List<OrderSummaryInterface> getAllOrderSummariesByCustomer(UUID customerId, Pageable pageable);

    @Query(value = """
        SELECT Count(*)
        FROM orders o
        WHERE o.customer_id = ?1
        """, nativeQuery = true)
    int countAllOrderSummariesByCustomer(UUID customerId);

    @Query(value = """
        SELECT 
            BIN_TO_UUID(o.id) AS id, 
            o.created_at AS createdAt, 
            o.payment_method AS paymentMethod,
            o.total_price AS totalPrice,
            c.name AS customerName,
            u.first_name AS cashierName
        FROM orders o
        JOIN customers c ON o.customer_id = c.customer_id
        JOIN users u ON o.cashier_id = u.user_id
        WHERE o.cashier_id = ?1
        """, nativeQuery = true)
    List<OrderSummaryInterface> getAllOrderSummariesByCashier(UUID cashierId, Pageable pageable);

    @Query(value = """
        SELECT Count(*)
        FROM orders o
        WHERE o.cashier_id = ?1
        """, nativeQuery = true)
    int countAllOrderSummariesByCashier(UUID cashierId);

    @Query(value = """
        SELECT 
            BIN_TO_UUID(o.id) AS id, 
            o.created_at AS createdAt, 
            o.payment_method AS paymentMethod,
            o.total_price AS totalPrice,
            c.name AS customerName,
            u.first_name AS cashierName
        FROM orders o
        JOIN customers c ON o.customer_id = c.customer_id
        JOIN users u ON o.cashier_id = u.user_id
        """, nativeQuery = true)
    List<OrderSummaryInterface> getAllOrders(Pageable pageable);

    @Query(value = """
        SELECT 
            BIN_TO_UUID(o.id) AS orderId, 
            o.created_at AS createdAt,
            o.total_price AS totalPrice,
            c.name AS customerName,
            c.mobile_no as customerMobile,
            c.email as customerEmail
        FROM orders o
        JOIN customers c ON o.customer_id = c.customer_id
        WHERE o.id = ?1
        """, nativeQuery = true)
    OrderDetailInterface getOrderDetailsByID(UUID id);

    @Query(value = """
        SELECT 
            p.name as productName, 
            p.unit_price as unitPrice,
            oi.quantity as quantity,
            oi.amount as amount
        FROM order_items oi
        JOIN products p ON oi.product_id = p.id 
        WHERE oi.order_id = ?1
        """, nativeQuery = true)
    List<OrderItemDetailInterface> getOrderItemsByOrderId(UUID id);

    Order getOrderById(UUID id);
}
