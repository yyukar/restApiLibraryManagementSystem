package com.example.Library.Management.System.service.impl;

import com.example.Library.Management.System.core.exceptions.NotFoundException;
import com.example.Library.Management.System.domain.entity.Book;
import com.example.Library.Management.System.domain.entity.Category;
import com.example.Library.Management.System.repository.AuthorRepository;
import com.example.Library.Management.System.repository.BookRepository;
import com.example.Library.Management.System.repository.CategoryRepository;
import com.example.Library.Management.System.repository.PublisherRepository;
import com.example.Library.Management.System.service.interfaces.BookService;
import com.example.Library.Management.System.web.dto.book.BookRequest;
import com.example.Library.Management.System.web.dto.book.BookResponse;
import com.example.Library.Management.System.web.dto.book.BookUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Override
    public BookResponse create(BookRequest req) {
        Book b = new Book();
        b.setName(req.getName());
        b.setPublicationYear(req.getPublicationYear());
        b.setStock(req.getStock());
        b.setAuthor(authorRepository.findById(req.getAuthorId()).orElseThrow(() -> new NotFoundException("Yazar bulunamadı: "+req.getAuthorId())));
        b.setPublisher(publisherRepository.findById(req.getPublisherId()).orElseThrow(() -> new NotFoundException("Yayınevi bulunamadı: "+req.getPublisherId())));
        Set<Category> cats = new HashSet<>(categoryRepository.findAllById(req.getCategoryIds()));
        if (cats.size() != req.getCategoryIds().size()) throw new NotFoundException("Kategori ID'lerinden bazıları bulunamadı");
        b.setCategories(cats);
        Book saved = bookRepository.save(b);
        return mapper.map(saved, BookResponse.class);
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponse get(Long id) {
        Book b = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Kitap bulunamadı: "+id));
        return mapper.map(b, BookResponse.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> list() {
        return bookRepository.findAll().stream().map(b -> mapper.map(b, BookResponse.class)).toList();
    }

    @Override
    public BookResponse update(Long id, BookUpdateRequest req) {
        Book b = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Kitap bulunamadı: "+id));
        b.setName(req.getName());
        b.setPublicationYear(req.getPublicationYear());
        b.setStock(req.getStock());
        b.setAuthor(authorRepository.findById(req.getAuthorId()).orElseThrow(() -> new NotFoundException("Yazar bulunamadı: "+req.getAuthorId())));
        b.setPublisher(publisherRepository.findById(req.getPublisherId()).orElseThrow(() -> new NotFoundException("Yayınevi bulunamadı: "+req.getPublisherId())));
        Set<Category> cats = new HashSet<>(categoryRepository.findAllById(req.getCategoryIds()));
        if (cats.size() != req.getCategoryIds().size()) throw new NotFoundException("Kategori ID'lerinden bazıları bulunamadı");
        b.setCategories(cats);
        return mapper.map(b, BookResponse.class);
    }

    @Override
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) throw new NotFoundException("Kitap bulunamadı: "+id);
        bookRepository.deleteById(id);
    }
}
