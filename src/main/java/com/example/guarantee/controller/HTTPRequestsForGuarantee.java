package com.example.guarantee.controller;

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
    public Device addNewDevice(@RequestBody Device device) {
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
    public Warranty addWarrenty(@RequestBody Warranty warranty, @PathVariable long productId) {
        Device device = deviceRepository.findById(productId).orElse(null);
        if (device != null) {
            warranty.setDevice(device);
            warranty.setPurchasedDate(String.valueOf(LocalDate.now()));
            /* check date with guaranteeTime
             * if needed update status of warranty  */
            return warrantyRepository.save(warranty);
        } else {
            //System.out.println("no devices with that id");
        }
        return null;
    }
}
