package com.example.guarantee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link com.example.guarantee.entity.Warranty}
 */
@AllArgsConstructor
@Getter
public class WarrantyDto implements Serializable {
    private final Long id;
    private final String purchasedDate;
    private final int guaranteeTime;
    private final boolean warrantyStatus;
    private final DeviceDto device;
}