package com.FileTransfer.controller;

import com.FileTransfer.service.FTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.IOException;

@RestController
public class HomeController {

    @Autowired
    FTService service;

    @GetMapping("/readFile")
    void readFile() throws IOException {
        service.readPDF();
    }

    @GetMapping("/getMultipartFile")
    ResponseEntity<Resource> getMultipartFile() throws IOException {
        byte[] pdfContent = service.readPDF();
        MultipartFile multipartFile = service.convertToMultipartFile(pdfContent, "example.pdf", "application/pdf");

        // Return ResponseEntity<Resource> directly using multipartFile.getResource()
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + multipartFile.getName() + "\"")
                .body(multipartFile.getResource());
    }

}
