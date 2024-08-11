package com.example.guarantee.mapper;

import com.example.guarantee.dto.WarrantyDto;
import com.example.guarantee.entity.Warranty;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {DeviceMapper.class})
public interface WarrantyMapper {
    Warranty toEntity(WarrantyDto warrantyDto);

    WarrantyDto toDto(Warranty warranty);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Warranty partialUpdate(WarrantyDto warrantyDto, @MappingTarget Warranty warranty);
}