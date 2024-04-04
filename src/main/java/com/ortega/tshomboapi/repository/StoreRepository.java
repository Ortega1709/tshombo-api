package com.ortega.tshomboapi.repository;

import com.ortega.tshomboapi.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM store WHERE user_id = :userId")
    Optional<Store> findStoreByUserId(@Param("userId") Long id);

    @Query(nativeQuery = true, value = "UPDATE store SET image = :image WHERE store_id = :storeId")
    void updateStoreImage(@Param("image") String image, @Param("storeId") Long id);

}
