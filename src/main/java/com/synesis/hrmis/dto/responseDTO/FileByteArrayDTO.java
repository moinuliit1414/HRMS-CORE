package com.synesis.hrmis.dto.responseDTO;

import lombok.Data;

@Data
public class FileByteArrayDTO {
    private byte[] file;
    private String extension;
    private Long size;
    private String fileName;
}
