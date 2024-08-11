package com.example.guarantee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link com.example.guarantee.entity.Device}
 */
@AllArgsConstructor
@Getter
public class DeviceDto implements Serializable {
    private final long id;
    private final int serialNumber;
    private final String model;
    private final String brand;
}