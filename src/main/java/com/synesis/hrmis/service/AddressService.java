package com.synesis.hrmis.service;

import com.synesis.hrmis.domain.Address;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {
    ResponseEntity<?> saveAddressInfo(Address addressInfo);
    ResponseEntity<?> getAddressInfo(long employeeId);
    ResponseEntity<?> getAllAddressInfo();
    ResponseEntity<?> updateAddressInfo(Address addressInfo);
    ResponseEntity<?> deleteAddressInfo(long id);
}
