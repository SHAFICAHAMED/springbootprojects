/*
package com.example.OCR.service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@Service
public class PdfOcrService {

    public String extractTextFromPdf(File pdfFile) throws Exception {
        PDDocument document = PDDocument.load(pdfFile);
        PDFRenderer renderer = new PDFRenderer(document);

        Tesseract tesseract = new Tesseract();

    // ✅ This line uses absolute path to your tessdata folder
        tesseract.setDatapath(new File("tessdata").getAbsolutePath());

        tesseract.setLanguage("tam"); // ✅ File name is eng.traineddata

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage image = renderer.renderImageWithDPI(i, 300);
            String text = tesseract.doOCR(image);
            result.append(text).append("\n");
        }

        document.close();
        return result.toString();
    }
}
*/

package com.example.OCR.service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

@Service
public class PdfOcrService {

    public String extractTextFromPdf(File pdfFile) throws Exception {
        PDDocument document = PDDocument.load(pdfFile);
        PDFRenderer renderer = new PDFRenderer(document);

        //  Configure Tesseract
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(new File("tessdata").getAbsolutePath()); // Path to tessdata
        tesseract.setLanguage("tam"); // Use both Tamil and English
        tesseract.setOcrEngineMode(1); // Use LSTM only (best for Tamil)
        tesseract.setPageSegMode(6);   // Assume a single uniform block of text
        tesseract.setTessVariable("user_defined_dpi", "400"); // Help tesseract understand DPI

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage image = renderer.renderImageWithDPI(i, 400); // High-quality image
            BufferedImage grayImage = convertToGrayscale(image);       // Preprocess to grayscale

            String text = tesseract.doOCR(grayImage);
            result.append("Page ").append(i + 1).append(":\n").append(text).append("\n\n");
        }

        document.close();
        return result.toString();
    }

    //  Convert image to grayscale
    private BufferedImage convertToGrayscale(BufferedImage original) {
        BufferedImage grayscale = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d = grayscale.createGraphics();
        g2d.drawImage(original, 0, 0, null);
        g2d.dispose();
        return grayscale;
    }
}
