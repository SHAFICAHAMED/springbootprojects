package com.example.OCR.service;

import net.sourceforge.tess4j.Tesseract;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;

@Service
public class PdfOcrService {

    public String extractTextFromPdf(MultipartFile file) {
        System.out.println("📥 File received");

        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(new File("tessdata").getAbsolutePath());
        tesseract.setLanguage("eng");

        System.out.println("🔧 OCR setup complete");

        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            System.out.println("📄 PDF loaded");

            PDFRenderer renderer = new PDFRenderer(document);
            BufferedImage image = renderer.renderImageWithDPI(0, 300);

            System.out.println("🖼️ Image rendered");

            long start = System.currentTimeMillis();
            String result = tesseract.doOCR(image);
            long end = System.currentTimeMillis();

            System.out.println("✅ OCR done in " + (end - start) + " ms");

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return "❌ OCR failed: " + e.getMessage();
        }
    }
}
