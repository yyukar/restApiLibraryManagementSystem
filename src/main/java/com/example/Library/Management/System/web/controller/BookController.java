package com.example.Library.Management.System.web.controller;

import com.example.Library.Management.System.core.results.ApiResponse;
import com.example.Library.Management.System.service.interfaces.BookService;
import com.example.Library.Management.System.web.dto.book.BookRequest;
import com.example.Library.Management.System.web.dto.book.BookResponse;
import com.example.Library.Management.System.web.dto.book.BookUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<ApiResponse<BookResponse>> create(@Valid @RequestBody BookRequest req) {
        BookResponse created = bookService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(created));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BookResponse>>> list() {
        List<BookResponse> books = bookService.list();
        return ResponseEntity.ok(ApiResponse.ok(books));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponse>> get(@PathVariable Long id) {
        BookResponse book = bookService.get(id);
        return ResponseEntity.ok(ApiResponse.ok(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody BookUpdateRequest req) {
        BookResponse updated = bookService.update(id, req);
        return ResponseEntity.ok(ApiResponse.ok(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.ok(ApiResponse.okMessage("Kitap silindi"));
    }
}


