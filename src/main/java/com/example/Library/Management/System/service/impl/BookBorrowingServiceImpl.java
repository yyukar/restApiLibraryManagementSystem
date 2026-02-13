package com.example.Library.Management.System.service.impl;

import com.example.Library.Management.System.core.exceptions.NotFoundException;
import com.example.Library.Management.System.domain.entity.Book;
import com.example.Library.Management.System.domain.entity.BookBorrowing;
import com.example.Library.Management.System.repository.BookBorrowingRepository;
import com.example.Library.Management.System.repository.BookRepository;
import com.example.Library.Management.System.service.interfaces.BookBorrowingService;
import com.example.Library.Management.System.web.dto.borrowing.BookBorrowingCreateRequest;
import com.example.Library.Management.System.web.dto.borrowing.BookBorrowingResponse;
import com.example.Library.Management.System.web.dto.borrowing.BookBorrowingUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookBorrowingServiceImpl implements BookBorrowingService {
    private final BookBorrowingRepository borrowingRepository;
    private final BookRepository bookRepository;

    @Override
    public BookBorrowingResponse create(BookBorrowingCreateRequest req) {
        Book book = findBook(req.getBookId());
        ensureStockAvailable(book);
        book.setStock(book.getStock() - 1);

        BookBorrowing bb = new BookBorrowing();
        bb.setBook(book);
        bb.setBorrowerName(req.getBorrowerName());
        bb.setBorrowerEmail(req.getBorrowerEmail());
        bb.setBorrowingDate(req.getBorrowingDate() != null ? req.getBorrowingDate() : LocalDate.now());
        bb.setReturnDate(null);

        BookBorrowing saved = borrowingRepository.save(bb);
        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public BookBorrowingResponse get(Long id) {
        return toResponse(findBorrowing(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookBorrowingResponse> list() {
        return borrowingRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public BookBorrowingResponse update(Long id, BookBorrowingUpdateRequest req) {
        BookBorrowing borrowing = findBorrowing(id);

        if (borrowing.getBook() == null || !borrowing.getBook().getId().equals(req.getBookId())) {
            moveBorrowingToBook(borrowing, req.getBookId());
        }

        borrowing.setBorrowerName(req.getBorrowerName());
        return toResponse(borrowing);
    }

    @Override
    public void delete(Long id) {
        BookBorrowing borrowing = findBorrowing(id);
        if (borrowing.getReturnDate() == null && borrowing.getBook() != null) {
            Book book = borrowing.getBook();
            book.setStock((book.getStock() == null ? 0 : book.getStock()) + 1);
        }
        borrowingRepository.delete(borrowing);
    }

    private BookBorrowing findBorrowing(Long id) {
        return borrowingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Kayıt bulunamadı: " + id));
    }

    private Book findBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Kitap bulunamadı: " + id));
    }

    private void ensureStockAvailable(Book book) {
        if (book.getStock() == null || book.getStock() <= 0) {
            throw new IllegalStateException("Yeterli stok yok");
        }
    }

    private void moveBorrowingToBook(BookBorrowing borrowing, Long newBookId) {
        Book oldBook = borrowing.getBook();
        Book newBook = findBook(newBookId);

        if (borrowing.getReturnDate() == null) {
            if (oldBook != null) {
                oldBook.setStock((oldBook.getStock() == null ? 0 : oldBook.getStock()) + 1);
            }
            ensureStockAvailable(newBook);
            newBook.setStock(newBook.getStock() - 1);
        }

        borrowing.setBook(newBook);
    }

    private BookBorrowingResponse toResponse(BookBorrowing borrowing) {
        BookBorrowingResponse response = new BookBorrowingResponse();
        response.setId(borrowing.getId());
        response.setBookId(borrowing.getBook() != null ? borrowing.getBook().getId() : null);
        response.setBorrowerName(borrowing.getBorrowerName());
        response.setBorrowerEmail(borrowing.getBorrowerEmail());
        response.setBorrowingDate(borrowing.getBorrowingDate());
        response.setReturnDate(borrowing.getReturnDate());
        return response;
    }
}

