package com.barcode.ppigcycle_back.service;

import com.barcode.ppigcycle_back.domain.BarCode;
import com.barcode.ppigcycle_back.domain.dto.BarcodeDto;
import com.barcode.ppigcycle_back.exception.BarcodeNotFoundException;
import com.barcode.ppigcycle_back.repository.BarcodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BarcodeService {
    private final BarcodeRepository barcodeRepository;

    public BarcodeDto getProductInfoByBarcode(String barcodeNumber){
        BarCode barCode = barcodeRepository.findByBarcodeNumber(barcodeNumber)
                .orElseThrow(() -> new BarcodeNotFoundException(barcodeNumber));
        return new BarcodeDto(barCode.getGoods_name(), barCode.getHow(), barCode.getMethod());
    }


}