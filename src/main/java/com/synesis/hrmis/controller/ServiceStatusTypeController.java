package com.synesis.hrmis.controller;


import com.synesis.hrmis.service.ServiceStatusTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ServiceStatusTypeController {
    private final ServiceStatusTypeService serviceStatusTypeService;

    public ServiceStatusTypeController(ServiceStatusTypeService serviceStatusTypeService) {
        this.serviceStatusTypeService = serviceStatusTypeService;
    }

    @GetMapping("/service-status-type")
    public ResponseEntity<?> getAllServiceStatus() {
        return serviceStatusTypeService.getAllServiceStatusType();
    }

    @GetMapping("/service-status-type/{id}")
    public ResponseEntity<?> getAllServiceStatusById(@PathVariable(name = "id") long serviceStatusId) {
        return serviceStatusTypeService.getAllServiceStatusTypeById(serviceStatusId);
    }

    @PostMapping("/service-status-type")
    public ResponseEntity<?> saveServiceStatusInfo(@RequestBody Object serviceStatus) {
        return serviceStatusTypeService.saveServiceStatusTypeInfo(serviceStatus);
    }

    @PutMapping("/service-status-type/{id}")
    public ResponseEntity<?> updateServiceStatusInfo(@PathVariable("id") long serviceStatusId, @RequestBody Object serviceStatus) {
        return serviceStatusTypeService.updateServiceStatusTypeInfo(serviceStatusId, serviceStatus);
    }

    @DeleteMapping("/service-status-type/{id}")
    public ResponseEntity<?> deleteServiceStatus(@PathVariable(name = "id") long serviceStatusId) {
        return serviceStatusTypeService.deleteServiceStatusType(serviceStatusId);
    }
}
