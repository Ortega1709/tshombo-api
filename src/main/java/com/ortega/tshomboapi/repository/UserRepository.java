package com.ortega.tshomboapi.repository;

import com.ortega.tshomboapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);

    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE username = :username")
    Optional<User> findUserByUsername(@Param("username") String username);


}
