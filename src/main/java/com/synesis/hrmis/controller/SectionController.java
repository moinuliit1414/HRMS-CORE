package com.synesis.hrmis.controller;


import com.synesis.hrmis.service.SectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class SectionController {

    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/section")
    public ResponseEntity<?> getAllSection() {
        return sectionService.getAllSection();
    }

    @GetMapping("/section/{id}")
    public ResponseEntity<?> getAllSectionById(@PathVariable(name = "id") long getSectionById) {
        return sectionService.getAllSectionById(getSectionById);
    }

    @PostMapping("/section")
    public ResponseEntity<?> saveSectionInfo(@RequestBody Object section) {
        return sectionService.saveSectionInfo(section);
    }

    @PutMapping("/section/{id}")
    public ResponseEntity<?> updateSectionInfo(@PathVariable("id") long sectionId, @RequestBody Object sectionInfo) {
        return sectionService.updateSectionInfo(sectionId, sectionInfo);
    }

    @DeleteMapping("/section/{id}")
    public ResponseEntity<?> deleteSection(@PathVariable(name = "id") long sectionId) {
        return sectionService.deleteSection(sectionId);
    }
}
