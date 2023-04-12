package com.barcode.ppigcycle_back.repository;

import com.barcode.ppigcycle_back.domain.BarCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BarcodeRepository extends JpaRepository<BarCode, Integer> {
    Optional<BarCode> findByBarcodeNumber(String barcodeNumber);


}