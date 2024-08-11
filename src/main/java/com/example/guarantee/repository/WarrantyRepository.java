package com.example.guarantee.repository;

import com.example.guarantee.entity.Warranty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarrantyRepository extends JpaRepository<Warranty, Long> {
}
