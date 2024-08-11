package com.example.guarantee.mapper;

import com.example.guarantee.dto.DeviceDto;
import com.example.guarantee.entity.Device;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DeviceMapper {
    Device toEntity(DeviceDto deviceDto);

    DeviceDto toDto(Device device);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Device partialUpdate(DeviceDto deviceDto, @MappingTarget Device device);
}