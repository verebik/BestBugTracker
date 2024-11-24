package org.BBT.data.repository;

import org.BBT.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUsernameContainingIgnoreCase(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
