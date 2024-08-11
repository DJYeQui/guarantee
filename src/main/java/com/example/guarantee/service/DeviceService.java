package com.example.guarantee.service;

import com.example.guarantee.dto.DeviceDto;
import com.example.guarantee.entity.Device;
import com.example.guarantee.mapper.DeviceMapper;
import com.example.guarantee.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    public ResponseEntity<Object> sell(DeviceDto dto) {
        Device device = deviceRepository.save(deviceMapper.toEntity(dto));
        return ResponseEntity.ok(device);
    }
}
