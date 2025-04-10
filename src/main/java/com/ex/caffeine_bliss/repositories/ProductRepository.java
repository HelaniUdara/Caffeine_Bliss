package com.ex.caffeine_bliss.repositories;

import com.ex.caffeine_bliss.entities.Product;
import com.ex.caffeine_bliss.entities.enums.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsByName(String name);

    Product findByName(String name);

    List<Product> findAllByCategory(ProductCategory category);
}
