package com.example.library_management.controller;


import com.example.library_management.model.Book;
import com.example.library_management.model.Student;
import com.example.library_management.repository.BookRepository;
import com.example.library_management.repository.StudentRepository;
import com.example.library_management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final BookService bookService;
    private final StudentRepository studentRepository;

    @Autowired
    private BookRepository bookRepository;


    public ApiController(BookService bookService, StudentRepository studentRepository) {
        this.bookService = bookService;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/books")
    public List<Book> getBooks(@RequestParam(required = false) String search) {
        return bookService.allBooks(search);
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Map<String,String> payload) {
        Book b = new Book();
        b.setAuthor(payload.get("author"));
        b.setTitle(payload.get("title"));
        b.setAvailable(true);
        return bookService.save(b);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Map<String,String> payload) {
        Book book = bookService.findById(id).orElseThrow();
        book.setAuthor(payload.get("author"));
        book.setTitle(payload.get("title"));
        return bookService.save(book);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        if (bookService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        bookService.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Book deleted successfully"));
    }


    @PostMapping("/books/{id}/lend")
    public ResponseEntity<?> lend(@PathVariable Long id, @RequestBody Map<String,String> body) {
        String studentId = body.get("studentId");
        String result = bookService.lendBook(id, studentId);
        if ("Lent".equals(result)) return ResponseEntity.ok(Map.of("status", "ok"));
        return ResponseEntity.badRequest().body(Map.of("error", result));
    }

    @PostMapping("/books/{id}/return")
    public ResponseEntity<?> ret(@PathVariable Long id, @RequestBody Map<String,String> body) {
        String studentId = body.get("studentId");
        int fine = bookService.returnBook(id, studentId);
        return ResponseEntity.ok(Map.of("fine", fine));
    }

    @PostMapping("/student/login")
    public ResponseEntity<?> studentLogin(@RequestBody Map<String,String> data) {
        String sid = data.get("studentId");
        String pass = data.get("password");
        Student s = studentRepository.findById(sid).orElse(null);
        if (s == null) {
            // create demo student
            Student newS = new Student(sid, pass);
            studentRepository.save(newS);
            return ResponseEntity.ok(Map.of("ok", true));
        }
        if (s.getPassword().equals(pass)) return ResponseEntity.ok(Map.of("ok", true));
        return ResponseEntity.status(401).body(Map.of("ok", false));
    }

    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody Map<String,String> data) {

        String id = data.get("id");
        String pass = data.get("password");
        if ("101".equals(id) && "201".equals(pass)) return ResponseEntity.ok(Map.of("ok", true));
        return ResponseEntity.status(401).body(Map.of("ok", false));
    }
}

