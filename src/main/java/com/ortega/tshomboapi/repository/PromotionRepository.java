package com.ortega.tshomboapi.repository;

import com.ortega.tshomboapi.model.Promotion;
import com.ortega.tshomboapi.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM promotion WHERE store_id = :storeId")
    Optional<Promotion> findPromotionByStoreId(@Param("storeId") Long id);

    @Query(nativeQuery = true, value = "UPDATE promotion SET image = :image WHERE promotion_id = :promotionId")
    void updatePromotionImage(@Param("image") String image, @Param("promotionId") Long id);

    @Query(nativeQuery = true, value = "DELETE FROM promotion WHERE store_store_id = :storeId")
    void deletePromotionByStoreId(@Param("storeId") Long id);
}
