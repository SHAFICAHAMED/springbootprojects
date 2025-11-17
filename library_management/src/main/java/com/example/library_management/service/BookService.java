package com.example.library_management.service;


import com.example.library_management.model.Book;
import com.example.library_management.model.Student;
import com.example.library_management.repository.BookRepository;
import com.example.library_management.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;

    public static final int MAX_BORROW = 3;
    public static final int BORROW_DAYS = 7;
    public static final int FINE_PER_DAY = 5;



    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }



    public BookService(BookRepository br, StudentRepository sr) {
        this.bookRepository = br;
        this.studentRepository = sr;
    }

    public List<Book> allBooks(String search) {
        if (search == null || search.isBlank()) return bookRepository.findAll();
        return bookRepository.findByAuthorContainingIgnoreCaseOrTitleContainingIgnoreCase(search, search);
    }

    public Book save(Book b){ return bookRepository.save(b); }

    public Optional<Book> findById(Long id) { return bookRepository.findById(id); }

    @Transactional
    public String lendBook(Long bookId, String studentId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        Student stud = studentRepository.findById(studentId).orElse(null);
        if (stud == null) {

            stud = new Student(studentId, "password");
            studentRepository.save(stud);
        }
        if (!book.isAvailable()) return "Book not available";
        if (stud.getBorrowedCount() >= MAX_BORROW) return "Borrow limit reached";

        book.setAvailable(false);
        book.setBorrowedBy(studentId);
        book.setDueDate(LocalDate.now().plusDays(BORROW_DAYS));
        stud.setBorrowedCount(stud.getBorrowedCount() + 1);

        bookRepository.save(book);
        studentRepository.save(stud);
        return "Lent";
    }

    @Transactional
    public int returnBook(Long bookId, String studentId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        Student stud = studentRepository.findById(studentId).orElse(null);
        int fine = 0;
        if (book.getDueDate() != null) {
            LocalDate due = book.getDueDate();
            if (LocalDate.now().isAfter(due)) {
                fine = (int) (java.time.temporal.ChronoUnit.DAYS.between(due, LocalDate.now())) * FINE_PER_DAY;
            }
        }
        book.setAvailable(true);
        book.setBorrowedBy(null);
        book.setDueDate(null);
        if (stud != null) {
            stud.setBorrowedCount(Math.max(0, stud.getBorrowedCount() - 1));
            studentRepository.save(stud);
        }
        bookRepository.save(book);
        return fine;
    }




}

