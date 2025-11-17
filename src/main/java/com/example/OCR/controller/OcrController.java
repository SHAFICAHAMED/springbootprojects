package com.example.OCR.controller;

import com.example.OCR.service.PdfOcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/ocr")
public class OcrController {

    @Autowired
    private PdfOcrService pdfOcrService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPdf(@RequestParam("file") MultipartFile file) {
        String extractedText = pdfOcrService.extractTextFromPdf(file);
        return ResponseEntity.ok(extractedText);
    }
}
