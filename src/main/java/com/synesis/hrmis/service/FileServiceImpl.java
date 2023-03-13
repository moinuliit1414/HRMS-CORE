package com.synesis.hrmis.service;

import com.synesis.hrmis.dto.responseDTO.ErrorResponse;
import com.synesis.hrmis.dto.responseDTO.FileByteArrayDTO;
import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService{
    @Override
    public ResponseEntity<?> uploadFile(MultipartFile file, String folderName) {

        try {
            byte[] bytes = file.getBytes();

            Path path = Paths.get("/employee-data/" + folderName);
            Files.createDirectories(path);
            String filePathName= path + "/" + file.getOriginalFilename();

            BufferedOutputStream outputStream =new BufferedOutputStream(new FileOutputStream(filePathName));
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse<>("Failed to upload files"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new SuccessResponse<>("Successfully uploaded files"), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> downloadFile(String fileURL) {
        Optional<FileByteArrayDTO> byteArrayFromFile = getByteArrayFromFile(fileURL);
        if(byteArrayFromFile.isPresent()) {
            return new ResponseEntity<>(new SuccessResponse<>(byteArrayFromFile.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse<>("Failed to download files"), HttpStatus.OK);
    }

    private Optional<FileByteArrayDTO> getByteArrayFromFile(String path) {
        FileByteArrayDTO fileByteArrayDTO = new FileByteArrayDTO();
        File file;
        try {
            byte[] fileStream;
            file = new File(path);
            Path absPath = Paths.get(file.getAbsolutePath());
            fileStream = Files.readAllBytes(absPath);
            fileByteArrayDTO.setFile(fileStream);
            fileByteArrayDTO.setFileName(file.getName());
            try {
                String[] split = path.split("\\.");
                String ext = split[split.length - 1];
                fileByteArrayDTO.setExtension(ext);
                fileByteArrayDTO.setSize(file.length());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(fileByteArrayDTO);
    }
}
