package com.FileTransfer.service;

import com.FileTransfer.util.ByteArrToMultipartFIle;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@Service
public class FTServiceImpl implements FTService{

    public byte[] readPDF() throws IOException {
        File file = new File("C:/Users/Anushree M/Downloads/Sample pdf.pdf");
        PDDocument pdDocument = PDDocument.load(file);
        PDFTextStripper textStripper = new PDFTextStripper();
        String text = textStripper.getText(pdDocument);

        byte[] fileArr = text.getBytes();

        System.out.println(Arrays.toString(fileArr));

        pdDocument.close();

        return fileArr;
    }

    public MultipartFile convertToMultipartFile(byte[] fileArr, String fileName, String contentType) {
        return new ByteArrToMultipartFIle(fileArr, fileName, contentType);
    }

}
