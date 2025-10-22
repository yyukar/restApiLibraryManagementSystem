package com.example.Library.Management.System.service.interfaces;

import com.example.Library.Management.System.web.dto.borrowing.BookBorrowingCreateRequest;
import com.example.Library.Management.System.web.dto.borrowing.BookBorrowingResponse;
import com.example.Library.Management.System.web.dto.borrowing.BookBorrowingUpdateRequest;

import java.util.List;

public interface BookBorrowingService {
    BookBorrowingResponse create(BookBorrowingCreateRequest req); // stock check & decrement
    BookBorrowingResponse get(Long id);
    List<BookBorrowingResponse> list();
    BookBorrowingResponse update(Long id, BookBorrowingUpdateRequest req); // email not accepted here
    BookBorrowingResponse returnBook(Long id); // sets returnDate=now and increments stock
}