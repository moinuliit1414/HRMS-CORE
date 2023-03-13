package com.synesis.hrmis.controller;

import com.synesis.hrmis.service.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GradeController {
    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/grade")
    public ResponseEntity<?> getAllGrade() {
        return gradeService.getAllGrade();
    }

    @GetMapping("/grade/{id}")
    public ResponseEntity<?> getAllGradeById(@PathVariable(name = "id") long gradeById) {
        return gradeService.getAllGradeById(gradeById);
    }

    @PostMapping("/grade")
    public ResponseEntity<?> saveGradeInfo(@RequestBody Object grade) {
        return gradeService.saveGradeInfo(grade);
    }

    @PutMapping("/grade/{id}")
    public ResponseEntity<?> updateGradeInfo(@PathVariable("id") long gradeId, @RequestBody Object gradeInfo) {
        return gradeService.updateGradeInfo(gradeId, gradeInfo);
    }

    @DeleteMapping("/grade/{id}")
    public ResponseEntity<?> deleteGrade(@PathVariable(name = "id") long gradeId) {
        return gradeService.deleteGrade(gradeId);
    }
}
