package com.ortega.tshomboapi.repository;

import com.ortega.tshomboapi.model.Phone;
import com.ortega.tshomboapi.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM phone WHERE store_id = :storeId")
    Optional<Phone> findPhoneByStoreId(@Param("storeId") Long id);

}
