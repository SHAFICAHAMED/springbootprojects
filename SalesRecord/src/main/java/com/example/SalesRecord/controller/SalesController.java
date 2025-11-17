package com.example.SalesRecord.controller;
import com.example.SalesRecord.model.SalesRecordmodel;
import com.example.SalesRecord.repo.SalesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = "http://localhost:4200") // Allow Angular frontend
public class SalesController {

    private final SalesRepository salesRepository;

    public SalesController(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    // Fetch all sales records
    @GetMapping
    public List<SalesRecordmodel> getAllSales() {
        return salesRepository.findAll();
    }

    // Add new sales record
    @PostMapping
    public SalesRecordmodel addSale(@RequestBody SalesRecordmodel salesRecord) {
        return salesRepository.save(salesRecord);
    }
}
