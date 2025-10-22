package com.example.Library.Management.System.web.controller;

import com.example.Library.Management.System.core.results.ApiResponse;
import com.example.Library.Management.System.service.interfaces.BookBorrowingService;
import com.example.Library.Management.System.web.dto.borrowing.BookBorrowingCreateRequest;
import com.example.Library.Management.System.web.dto.borrowing.BookBorrowingResponse;
import com.example.Library.Management.System.web.dto.borrowing.BookBorrowingUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowings")
@RequiredArgsConstructor
public class BookBorrowingController {
    private final BookBorrowingService service;

    @PostMapping
    public ResponseEntity<ApiResponse<BookBorrowingResponse>> create(@Valid @RequestBody BookBorrowingCreateRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(service.create(req)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookBorrowingResponse>> get(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.get(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BookBorrowingResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.ok(service.list()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BookBorrowingResponse>> update(@PathVariable Long id, @Valid @RequestBody BookBorrowingUpdateRequest req) {
        return ResponseEntity.ok(ApiResponse.ok(service.update(id, req)));
    }

    @PatchMapping("/{id}/return")
    public ResponseEntity<ApiResponse<BookBorrowingResponse>> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.returnBook(id)));
    }
}
