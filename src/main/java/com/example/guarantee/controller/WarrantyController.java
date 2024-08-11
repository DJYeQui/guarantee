package com.example.guarantee.controller;

import com.example.guarantee.constants.ApiConstants;
import com.example.guarantee.dto.WarrantyDto;
import com.example.guarantee.service.WarrentyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.WARRANTY_API)
@RequiredArgsConstructor
public class WarrantyController {

    private final WarrentyService warrentyService;

    @PostMapping("/create-warranty")
    public ResponseEntity<Object> createWarranty(WarrantyDto dto) {
        return warrentyService.createWarranty(dto);
    }
}
