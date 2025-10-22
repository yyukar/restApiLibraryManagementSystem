package com.example.Library.Management.System.web.controller;

import com.example.Library.Management.System.core.results.ApiResponse;
import com.example.Library.Management.System.service.interfaces.AuthorService;
import com.example.Library.Management.System.web.dto.author.AuthorRequest;
import com.example.Library.Management.System.web.dto.author.AuthorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService service;

    @PostMapping
    public ResponseEntity<ApiResponse<AuthorResponse>> create(@Valid @RequestBody AuthorRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(service.create(req)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AuthorResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.ok(service.list()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorResponse>> get(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.get(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorResponse>> update(@PathVariable Long id,
                                                              @Valid @RequestBody AuthorRequest req) {
        return ResponseEntity.ok(ApiResponse.ok(service.update(id, req)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.okMessage("Yazar silindi"));
    }
}

