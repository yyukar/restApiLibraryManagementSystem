package com.example.Library.Management.System.web.controller;

import com.example.Library.Management.System.core.results.ApiResponse;
import com.example.Library.Management.System.service.interfaces.CategoryService;
import com.example.Library.Management.System.web.dto.category.CategoryRequest;
import com.example.Library.Management.System.web.dto.category.CategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> create(@Valid @RequestBody CategoryRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(service.create(req)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> get(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.get(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.ok(service.list()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> update(@PathVariable Long id, @Valid @RequestBody CategoryRequest req) {
        return ResponseEntity.ok(ApiResponse.ok(service.update(id, req)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        String msg = service.delete(id);
        HttpStatus status = msg.contains("silinemedi") ? HttpStatus.CONFLICT : HttpStatus.OK;
        return ResponseEntity.status(status).body(new ApiResponse<>(status.is2xxSuccessful(), msg, null));
    }
}
