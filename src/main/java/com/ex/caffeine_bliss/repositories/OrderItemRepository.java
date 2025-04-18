package com.ex.caffeine_bliss.repositories;

import com.ex.caffeine_bliss.entities.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface OrderItemRepository extends JpaRepository<OrderItems, UUID> {
}
