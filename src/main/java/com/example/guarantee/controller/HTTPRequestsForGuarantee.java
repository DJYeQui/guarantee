package com.example.guarantee.controller;

import com.example.guarantee.dto.DeviceDto;
import com.example.guarantee.dto.WarrantyDto;
import com.example.guarantee.entity.Device;
import com.example.guarantee.entity.Warranty;
import com.example.guarantee.repository.DeviceRepository;
import com.example.guarantee.repository.WarrantyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/warranty")
@RequiredArgsConstructor
public class HTTPRequestsForGuarantee {

    private final DeviceRepository deviceRepository;
    private final WarrantyRepository warrantyRepository;

    @PostMapping("/addNewDevice")
    @ResponseStatus(HttpStatus.OK)
    public Device addNewDevice(@RequestBody DeviceDto deviceDto) {
        Device device = dtoToDevice(deviceDto);
        return deviceRepository.save(device);
    }

    @GetMapping("/findWithIdDevice/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Device findProduct(@PathVariable long id) {
        return deviceRepository.findById(id).orElse(null);
    }

    @GetMapping("/updateBrandOfDevice/{id}/{brand}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBrandOfDevice(@PathVariable Long id, @PathVariable String brand) {
        Optional<Device> device = deviceRepository.findDeviceById(id);
        //buna bak

        if (device.isPresent()) {
            device.get().setBrand(brand);
            deviceRepository.save(device.get());
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        //Optional öğren
    }


    @DeleteMapping("/deleteDevice")
    public String deleteDevice(@RequestBody Device device) {
        deviceRepository.delete(device);
        return "deletion is completed successfully";
    }

    @DeleteMapping("/deleteDeviceWithId/{id}")
    public void deleteDeviceWithId(@PathVariable long id) {
        deviceRepository.deleteById(id);
    }

    @PostMapping("/addGuaranteeToProduct/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Warranty addWarranty(@RequestBody WarrantyDto warrantyDto, @PathVariable long productId) {
        Device device = deviceRepository.findById(productId).orElse(null);

        if (device != null) {
            Warranty warranty = dtoToWarranty(warrantyDto);
            warranty.setDevice(device);
            /* check date with guaranteeTime
             * if needed update status of warranty  */
            return warrantyRepository.save(warranty);
        }
        return null;
    }

    @DeleteMapping("/deleteGuarantee/{id}")
    public void deleteGuarantee(@PathVariable long id) {
        warrantyRepository.deleteById(id);
    }

    // service map
    public Warranty dtoToWarranty(WarrantyDto warrantyDTO) {
        Warranty warranty = new Warranty();
        warranty.setGuaranteeTime(warrantyDTO.getGuaranteeTime());
        warranty.setWarrantyStatus(true);
        warranty.setPurchasedDate(String.valueOf(LocalDate.now()));
        // device is not defined
        return warranty;
    }

    //service
    public Device dtoToDevice(DeviceDto deviceDTO) {
        Device device = new Device();
        device.setBrand(deviceDTO.getBrand());
        device.setModel(deviceDTO.getModel());
        device.setSerialNumber(deviceDTO.getSerialNumber());
        return device;
    }
}
