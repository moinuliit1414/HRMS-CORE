package com.synesis.hrmis.controller;

import com.synesis.hrmis.service.ResultTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ResultTypeController {

    private final ResultTypeService resultTypeService;

    public ResultTypeController(ResultTypeService resultTypeService) {
        this.resultTypeService = resultTypeService;
    }

    @GetMapping("result-type")
    public ResponseEntity<?> getAllResultType() {
        return resultTypeService.getAllResultType();
    }

    @GetMapping("/result-type/{id}")
    public ResponseEntity<?> getResultTypeById(@PathVariable("id") long resultTypeID) {
        return resultTypeService.getResultTypeById(resultTypeID);
    }

    @PostMapping("/result-type")
    public ResponseEntity<?> saveResultTypeInfo(@RequestBody Object resultType) {
        return resultTypeService.saveResultType(resultType);
    }

    @PutMapping("/result-type/{id}")
    public ResponseEntity<?> updateResultTypeInfo(@PathVariable("id") long resultTypeId, @RequestBody Object resultType) {
        return resultTypeService.updateResultType(resultTypeId, resultType);
    }

    @DeleteMapping("/result-type/{id}")
    public ResponseEntity<?> deleteResultTypeById(@PathVariable("id") long resultTypeID) {
        return resultTypeService.deleteResultTypeById(resultTypeID);
    }
}
