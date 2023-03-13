package com.synesis.hrmis.service;

import com.synesis.hrmis.dto.responseDTO.FileByteArrayDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

@Service
public interface FileService {
    ResponseEntity<?> uploadFile(MultipartFile file, String folderName) throws IOException;
    ResponseEntity<?> downloadFile(String fileURL);
}
