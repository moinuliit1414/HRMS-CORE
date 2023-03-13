package com.synesis.hrmis.service.impl;

import com.synesis.hrmis.domain.ResultType;
import com.synesis.hrmis.domain.ServiceStatusType;
import com.synesis.hrmis.dto.responseDTO.ErrorResponse;
import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import com.synesis.hrmis.repository.ServiceStatusTypeRepository;
import com.synesis.hrmis.service.ServiceStatusTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceStatusServiceTypeImpl implements ServiceStatusTypeService {

    private final ServiceStatusTypeRepository serviceStatusTypeRepository;

    ModelMapper mapper = new ModelMapper();

    public ServiceStatusServiceTypeImpl(ServiceStatusTypeRepository serviceStatusTypeRepository) {
        this.serviceStatusTypeRepository = serviceStatusTypeRepository;
    }

    @Override
    public ResponseEntity<?> getAllServiceStatusType() {
        List<ServiceStatusType> serviceStatusTypes = serviceStatusTypeRepository.findAll();
        return new ResponseEntity<>(new SuccessResponse<>(serviceStatusTypes), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllServiceStatusTypeById(long serviceStatusId) {
        Optional<ServiceStatusType> serviceStatusTypes = serviceStatusTypeRepository.findById(serviceStatusId);
        if (serviceStatusTypes.isPresent()) {
            return new ResponseEntity(new SuccessResponse(serviceStatusTypes), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorResponse("Failed to get Service Status Type Data"), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> saveServiceStatusTypeInfo(Object serviceStatusTypeInfo) {
        ServiceStatusType serviceStatusType = mapper.map(serviceStatusTypeInfo, ServiceStatusType.class);
        try {
            serviceStatusTypeRepository.save(serviceStatusType);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(new ErrorResponse("Failed to Service Status Type Data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(new SuccessResponse("Successfully saved Service Status Type Data"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateServiceStatusTypeInfo(long serviceStatusId, Object serviceStatusTypeNewData) {
        Optional<ServiceStatusType> serviceStatusTypeExistData = serviceStatusTypeRepository.findById(serviceStatusId);
        if (serviceStatusTypeExistData.isPresent()) {
            mapper.map(serviceStatusTypeNewData, serviceStatusTypeExistData.get());
            return new ResponseEntity<>(serviceStatusTypeRepository.save(serviceStatusTypeExistData.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse("Internal Problem to Updated Service Status Type Data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteServiceStatusType(long serviceStatusId) {
        Optional<ServiceStatusType> serviceStatusType = serviceStatusTypeRepository.findById(serviceStatusId);
        if (serviceStatusType.isPresent()) {
            serviceStatusTypeRepository.delete(serviceStatusType.get());
            return new ResponseEntity(new SuccessResponse("Successfully deleted RService Status Type data"), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorResponse("Service Status Type Data does not exist"), HttpStatus.NOT_FOUND);

    }
}
