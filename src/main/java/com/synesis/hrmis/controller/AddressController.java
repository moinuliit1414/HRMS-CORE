package com.synesis.hrmis.controller;

import com.synesis.hrmis.domain.Address;
import com.synesis.hrmis.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/address")
    public ResponseEntity<?> getAllAddress() {
        return addressService.getAllAddressInfo();
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<?> getAddressInfo(@PathVariable(name = "id") long employeeId) {
        return addressService.getAddressInfo(employeeId);
    }

    @PostMapping("/address")
    public ResponseEntity<?> saveAddressInfo(@RequestBody Address addressInfo) {
        return addressService.saveAddressInfo(addressInfo);
    }

    @PutMapping("/address")
    public ResponseEntity<?> updateAddress(@RequestBody Address addressInfo) {
        return addressService.updateAddressInfo(addressInfo);
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable(name = "id") long id) {
        return addressService.deleteAddressInfo(id);
    }
}
