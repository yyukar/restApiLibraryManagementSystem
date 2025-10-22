package com.example.Library.Management.System.service.interfaces;

import com.example.Library.Management.System.web.dto.author.AuthorRequest;
import com.example.Library.Management.System.web.dto.author.AuthorResponse;

import java.util.List;

public interface AuthorService {
    AuthorResponse create(AuthorRequest req);
    AuthorResponse get(Long id);
    List<AuthorResponse> list();
    AuthorResponse update(Long id, AuthorRequest req);
    void delete(Long id);
}
