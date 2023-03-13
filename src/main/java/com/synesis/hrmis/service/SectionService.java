package com.synesis.hrmis.service;

import org.springframework.http.ResponseEntity;

public interface SectionService {
    ResponseEntity<?> getAllSection();

    ResponseEntity<?> getAllSectionById(long getSectionById);

    ResponseEntity<?> saveSectionInfo(Object section);

    ResponseEntity<?> updateSectionInfo(long sectionId, Object sectionInfo);

    ResponseEntity<?> deleteSection(long sectionId);
}
