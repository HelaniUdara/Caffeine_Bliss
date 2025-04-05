package com.ex.caffeine_bliss.repositories;

import com.ex.caffeine_bliss.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Customer findByMobileNo(String mobileNo);

    Customer findByName(String name);

    boolean existsByMobileNo(String mobileNo);
}
