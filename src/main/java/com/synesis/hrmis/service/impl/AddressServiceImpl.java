package com.synesis.hrmis.service.impl;

import com.synesis.hrmis.domain.Address;
import com.synesis.hrmis.dto.responseDTO.ErrorResponse;
import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import com.synesis.hrmis.repository.AddressRepository;
import com.synesis.hrmis.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    ModelMapper mapper = new ModelMapper();

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public ResponseEntity<?> saveAddressInfo(Address addressInfo) {
        try {
            addressInfo.setCreatedAt(LocalDate.now());
            addressRepository.save(addressInfo);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse<>("Failed to save address data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new SuccessResponse<>("Successfully saved address data"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAddressInfo(long employeeId) {
        List<Address> address = addressRepository.findAddressByEmployeeIdOrderByCreatedAt(employeeId);
        if(!address.isEmpty()) {
            return new ResponseEntity<>(new SuccessResponse<>(address), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse<>("Address Info not found"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllAddressInfo() {
        List<Address> addressList = addressRepository.findAll();
        return new ResponseEntity<>(new SuccessResponse<>(addressList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateAddressInfo(Address addressInfo) {
        Optional<Address> existingAddress = addressRepository.findAddressByIdAndEmployeeId(addressInfo.getId(), addressInfo.getEmployeeId());
        if(existingAddress.isPresent()) {
            try {
                mapper.map(addressInfo, existingAddress.get());
                existingAddress.get().setCreatedAt(LocalDate.now());
                addressRepository.save(existingAddress.get());
            } catch (Exception ex) {
                ex.printStackTrace();
                return new ResponseEntity<>(new ErrorResponse<>("Failed to update address data"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(new SuccessResponse<>("Successfully updated address data"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteAddressInfo(long id) {
        try {
            addressRepository.deleteById(id);
            return new ResponseEntity<>(new SuccessResponse<>("Successfully deleted address data"), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse<>("Failed to delete address data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
