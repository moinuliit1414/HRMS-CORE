package com.synesis.hrmis.service.impl;

import com.synesis.hrmis.domain.Grade;
import com.synesis.hrmis.domain.Section;
import com.synesis.hrmis.dto.responseDTO.ErrorResponse;
import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import com.synesis.hrmis.repository.SectionRepository;
import com.synesis.hrmis.service.SectionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;

    ModelMapper mapper = new ModelMapper();

    public SectionServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public ResponseEntity<?> getAllSection() {
        List<Section> sectionList = sectionRepository.findAll();
        return new ResponseEntity<>(new SuccessResponse<>(sectionList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllSectionById(long getSectionById) {
        Optional<Section> section = sectionRepository.findById(getSectionById);
        if (section.isPresent()) {
            return new ResponseEntity(new SuccessResponse(section), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorResponse("Failed to get Section Data"), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> saveSectionInfo(Object sectionInfo) {
        Section section = mapper.map(sectionInfo, Section.class);
        try {
            sectionRepository.save(section);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(new ErrorResponse("Failed to save Section Data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(new SuccessResponse("Successfully saved Section Data"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateSectionInfo(long sectionId, Object sectionNewInfo) {
        Optional<Section> sectionExistInfo = sectionRepository.findById(sectionId);
        if (sectionExistInfo.isPresent()) {
            mapper.map(sectionNewInfo, sectionExistInfo.get());
            return new ResponseEntity<>(sectionRepository.save(sectionExistInfo.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse("Internal Problem to Updated Section Data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<?> deleteSection(long sectionId) {
        Optional<Section> section = sectionRepository.findById(sectionId);
        if (section.isPresent()) {
            sectionRepository.delete(section.get());
            return new ResponseEntity(new SuccessResponse("Successfully deleted Section data"), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorResponse("Section Data does not exist"), HttpStatus.NOT_FOUND);

    }
}
