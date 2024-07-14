package com.FileTransfer.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FTService {

    byte[] readPDF() throws IOException;

    MultipartFile convertToMultipartFile(byte[] fileArr, String fileName, String contentType);

}
