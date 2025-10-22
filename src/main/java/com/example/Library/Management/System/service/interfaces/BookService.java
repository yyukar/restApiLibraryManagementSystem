package com.example.Library.Management.System.service.interfaces;

import com.example.Library.Management.System.web.dto.book.BookRequest;
import com.example.Library.Management.System.web.dto.book.BookResponse;
import com.example.Library.Management.System.web.dto.book.BookUpdateRequest;

import java.util.List;

public interface BookService {
    BookResponse create(BookRequest req);
    BookResponse get(Long id);
    List<BookResponse> list();
    BookResponse update(Long id, BookUpdateRequest req);
    void delete(Long id);
}
