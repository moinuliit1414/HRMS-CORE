package com.synesis.hrmis.service;

import org.springframework.http.ResponseEntity;

public interface ServiceStatusTypeService {
    ResponseEntity<?> getAllServiceStatusType();

    ResponseEntity<?> getAllServiceStatusTypeById(long serviceStatusId);

    ResponseEntity<?> saveServiceStatusTypeInfo(Object serviceStatus);

    ResponseEntity<?> updateServiceStatusTypeInfo(long serviceStatusId, Object serviceStatus);

    ResponseEntity<?> deleteServiceStatusType(long serviceStatusId);
}
