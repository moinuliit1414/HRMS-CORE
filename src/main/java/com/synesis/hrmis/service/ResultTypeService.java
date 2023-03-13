package com.synesis.hrmis.service;

import org.springframework.http.ResponseEntity;

public interface ResultTypeService {
    ResponseEntity<?> getAllResultType();

    ResponseEntity<?> getResultTypeById(long resultTypeID);

    ResponseEntity<?> saveResultType(Object resultType);

    ResponseEntity<?> updateResultType(long resultTypeId, Object resultType);

    ResponseEntity<?> deleteResultTypeById(long resultTypeID);
}
