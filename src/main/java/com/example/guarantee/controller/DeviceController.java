package com.example.guarantee.controller;

import com.example.guarantee.constants.ApiConstants;
import com.example.guarantee.dto.DeviceDto;
import com.example.guarantee.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstants.DEVICE_API)
@RequiredArgsConstructor
// put obje ez
//patch belirli bir özelliği update
//business servicelerde
// Vuetify
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping("/sell")
    //ResponseEntity<Object> hatayı da göster
    public ResponseEntity<Object> sell(@RequestBody DeviceDto dto) {
        return deviceService.sell(dto);
    }
}
