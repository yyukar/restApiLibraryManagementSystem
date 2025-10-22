package com.example.Library.Management.System.web.controller;

import com.example.Library.Management.System.core.results.ApiResponse;
import com.example.Library.Management.System.service.interfaces.PublisherService;
import com.example.Library.Management.System.web.dto.publisher.PublisherRequest;
import com.example.Library.Management.System.web.dto.publisher.PublisherResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService service;

    @PostMapping
    public ResponseEntity<ApiResponse<PublisherResponse>> create(@Valid @RequestBody PublisherRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(service.create(req)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PublisherResponse>> get(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.get(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PublisherResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.ok(service.list()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PublisherResponse>> update(@PathVariable Long id, @Valid @RequestBody PublisherRequest req) {
        return ResponseEntity.ok(ApiResponse.ok(service.update(id, req)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.okMessage("Yayınevi silindi"));
    }
}
