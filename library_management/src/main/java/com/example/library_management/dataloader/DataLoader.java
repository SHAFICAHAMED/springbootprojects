package com.example.library_management.dataloader;


import com.example.library_management.model.Book;
import com.example.library_management.model.Student;
import com.example.library_management.repository.BookRepository;
import com.example.library_management.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final BookRepository bookRepo;
    private final StudentRepository studentRepo;
    public DataLoader(BookRepository br, StudentRepository sr) { this.bookRepo = br; this.studentRepo = sr; }

    @Override
    public void run(String... args) throws Exception {
        if (bookRepo.count() == 0) {
            bookRepo.save(new Book("J.K. Rowling","Harry Potter"));
            bookRepo.save(new Book("George Orwell","1984"));
            bookRepo.save(new Book("Chetan Bhagat", "3 Mistakes of My Life"));
        }
        if (!studentRepo.existsById("201")) {
            studentRepo.save(new Student("201","pass"));
        }
    }
}
