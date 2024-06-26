package com.ortega.tshomboapi.repository;

import com.ortega.tshomboapi.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
