package com.example.guarantee.controller;

import com.example.guarantee.dto.DeviceDTO;
import com.example.guarantee.dto.WarrantyDTO;
import com.example.guarantee.entity.Device;
import com.example.guarantee.entity.Warranty;
import com.example.guarantee.repository.DeviceRepository;
import com.example.guarantee.repository.WarrantyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class HTTPRequestsForGuarantee {

    private final DeviceRepository deviceRepository;
    private final WarrantyRepository warrantyRepository;

    public HTTPRequestsForGuarantee(DeviceRepository deviceRepository, WarrantyRepository warrantyRepository) {
        this.deviceRepository = deviceRepository;
        this.warrantyRepository = warrantyRepository;
    }

    @PostMapping("/addNewDevice")
    @ResponseStatus(HttpStatus.OK)
    public Device addNewDevice(@RequestBody DeviceDTO deviceDTO) {
        Device device = dtoToDevice(deviceDTO);
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
        Device device = deviceRepository.findById(id).orElse(null);
        if (device != null) {
            device.setBrand(brand);
            deviceRepository.save(device);
        }
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
    public Warranty addWarranty(@RequestBody WarrantyDTO warrantyDTO, @PathVariable long productId) {
        Device device = deviceRepository.findById(productId).orElse(null);

        if (device != null) {
            Warranty warranty = dtoToWarranty(warrantyDTO);
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

    public Warranty dtoToWarranty(WarrantyDTO warrantyDTO) {
        Warranty warranty = new Warranty();
        warranty.setGuaranteeTime(warrantyDTO.getGuaranteeTime());
        warranty.setWarrantyStatus(true);
        warranty.setPurchasedDate(String.valueOf(LocalDate.now()));
        // device is not defined
        return warranty;
    }

    public Device dtoToDevice(DeviceDTO deviceDTO) {
        Device device = new Device();
        device.setBrand(deviceDTO.getBrand());
        device.setModel(deviceDTO.getModel());
        device.setSerialNumber(deviceDTO.getSerialNumber());
        return device;
    }
}
