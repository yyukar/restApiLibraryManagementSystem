package com.example.Library.Management.System.service.impl;

import com.example.Library.Management.System.core.exceptions.NotFoundException;
import com.example.Library.Management.System.domain.entity.Author;
import com.example.Library.Management.System.repository.AuthorRepository;
import com.example.Library.Management.System.service.interfaces.AuthorService;
import com.example.Library.Management.System.web.dto.author.AuthorRequest;
import com.example.Library.Management.System.web.dto.author.AuthorResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final ModelMapper mapper;

    @Override
    public AuthorResponse create(AuthorRequest req) {
        Author a = mapper.map(req, Author.class);
        return mapper.map(authorRepository.save(a), AuthorResponse.class);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorResponse get(Long id) {
        Author a = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Yazar bulunamadı: " + id));
        return mapper.map(a, AuthorResponse.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorResponse> list() {
        return authorRepository.findAll()
                .stream().map(a -> mapper.map(a, AuthorResponse.class)).toList();
    }

    @Override
    public AuthorResponse update(Long id, AuthorRequest req) {
        Author a = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Yazar bulunamadı: " + id));
        a.setName(req.getName());
        a.setBirthDate(req.getBirthDate());
        a.setCountry(req.getCountry());
        return mapper.map(a, AuthorResponse.class);
    }

    @Override
    public void delete(Long id) {
        if (!authorRepository.existsById(id))
            throw new NotFoundException("Yazar bulunamadı: " + id);
        authorRepository.deleteById(id);
    }
}

