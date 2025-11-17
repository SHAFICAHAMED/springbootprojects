package com.example.OCR.controller;

import com.example.OCR.service.PdfOcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/api/ocr")
public class OcrController {

    @Autowired
    private PdfOcrService pdfOcrService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPdf(@RequestParam("file") MultipartFile file) {
        try {
            // Save uploaded file to temp file
            File tempFile = File.createTempFile("upload-", ".pdf");
            file.transferTo(tempFile);

            // Extract text from PDF using OCR
            String extractedText = pdfOcrService.extractTextFromPdf(tempFile);

            return ResponseEntity.ok(extractedText);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to process PDF: " + e.getMessage());
        }
    }
}
