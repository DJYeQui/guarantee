package com.example.guarantee.service;

import com.example.guarantee.dto.WarrantyDto;
import com.example.guarantee.entity.Warranty;
import com.example.guarantee.mapper.WarrantyMapper;
import com.example.guarantee.repository.WarrantyRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class WarrentyService {
    private final WarrantyRepository warrantyRepository;
    private final WarrantyMapper warrantyMapper;

    public ResponseEntity<Object> createWarranty(WarrantyDto dto) {
        Warranty warranty = warrantyRepository.save(warrantyMapper.toEntity(dto));
        return ResponseEntity.ok(warranty);
    }
}
