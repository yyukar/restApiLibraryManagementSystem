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
import org.modelmapper.ModelMapper;
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
    private final ModelMapper mapper;

    @Override
    public BookBorrowingResponse create(BookBorrowingCreateRequest req) {
        Book book = bookRepository.findById(req.getBookId())
                .orElseThrow(() -> new NotFoundException("Kitap bulunamadı: " + req.getBookId()));
        if (book.getStock() == null || book.getStock() <= 0) {
            throw new IllegalStateException("Yeterli stok yok");
        }
        book.setStock(book.getStock() - 1);
        BookBorrowing bb = new BookBorrowing();
        bb.setBook(book);
        bb.setBorrowerName(req.getBorrowerName());
        bb.setBorrowerEmail(req.getBorrowerEmail());
        bb.setBorrowingDate(req.getBorrowingDate());
        bb.setReturnDate(null);
        BookBorrowing saved = borrowingRepository.save(bb);
        return mapper.map(saved, BookBorrowingResponse.class);
    }

    @Override
    @Transactional(readOnly = true)
    public BookBorrowingResponse get(Long id) {
        BookBorrowing bb = borrowingRepository.findById(id).orElseThrow(() -> new NotFoundException("Kayıt bulunamadı: " + id));
        return mapper.map(bb, BookBorrowingResponse.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookBorrowingResponse> list() {
        return borrowingRepository.findAll().stream().map(b -> mapper.map(b, BookBorrowingResponse.class)).toList();
    }

    @Override
    public BookBorrowingResponse update(Long id, BookBorrowingUpdateRequest req) {
        BookBorrowing bb = borrowingRepository.findById(id).orElseThrow(() -> new NotFoundException("Kayıt bulunamadı: " + id));
        if (req.getBorrowerName() != null) bb.setBorrowerName(req.getBorrowerName());
        if (req.getReturnDate() != null) bb.setReturnDate(req.getReturnDate());
        // borrowerEmail deliberately NOT accepted on update
        return mapper.map(bb, BookBorrowingResponse.class);
    }

    @Override
    public BookBorrowingResponse returnBook(Long id) {
        BookBorrowing bb = borrowingRepository.findById(id).orElseThrow(() -> new NotFoundException("Kayıt bulunamadı: " + id));
        if (bb.getReturnDate() == null) {
            bb.setReturnDate(LocalDate.now());
            Book book = bb.getBook();
            book.setStock(book.getStock() + 1);
        }
        return mapper.map(bb, BookBorrowingResponse.class);
    }
}
