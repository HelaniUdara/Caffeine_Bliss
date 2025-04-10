package com.ex.caffeine_bliss.repositories;

import com.ex.caffeine_bliss.DTOs.UserDTO;
import com.ex.caffeine_bliss.entities.User;
import com.ex.caffeine_bliss.entities.enums.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);

    List<UserDTO> findAllByActiveEquals(boolean b);

    Page<User> findAllByActiveEquals(boolean active, Pageable pageable);

    int countAllByActiveEquals(boolean active);

    List<UserDTO> findAllByActiveEqualsAndRoleEquals(boolean b, UserRole role);

    User findByEmail(String email);
}
