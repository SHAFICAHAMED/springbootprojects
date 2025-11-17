package com.example.SalesRecord.repo;



import com.example.SalesRecord.model.SalesRecordmodel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<SalesRecordmodel, Long> {
}
