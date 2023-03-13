package com.synesis.hrmis.controller;

import com.synesis.hrmis.dto.responseDTO.FileByteArrayDTO;
import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import com.synesis.hrmis.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("folderName") String folderName) throws IOException {
        return fileService.uploadFile(file, folderName);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName) {
        return fileService.downloadFile(fileName);
    }
}
