package com.example.Library.Management.System.service.impl;

import com.example.Library.Management.System.core.exceptions.NotFoundException;
import com.example.Library.Management.System.domain.entity.Category;
import com.example.Library.Management.System.repository.BookRepository;
import com.example.Library.Management.System.repository.CategoryRepository;
import com.example.Library.Management.System.service.interfaces.CategoryService;
import com.example.Library.Management.System.web.dto.category.CategoryRequest;
import com.example.Library.Management.System.web.dto.category.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    private final ModelMapper mapper;

    @Override
    public CategoryResponse create(CategoryRequest req) {
        Category c = mapper.map(req, Category.class);
        return mapper.map(categoryRepository.save(c), CategoryResponse.class);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse get(Long id) {
        Category c = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Kategori bulunamadı: " + id));
        return mapper.map(c, CategoryResponse.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> list() {
        return categoryRepository.findAll().stream().map(e -> mapper.map(e, CategoryResponse.class)).toList();
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest req) {
        Category c = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Kategori bulunamadı: " + id));
        c.setName(req.getName());
        c.setDescription(req.getDescription());
        return mapper.map(categoryRepository.save(c), CategoryResponse.class);
    }

    @Override
    public String delete(Long id) {
        if (!categoryRepository.existsById(id)) throw new NotFoundException("Kategori bulunamadı: " + id);
        boolean hasBooks = bookRepository.existsByCategoryId(id);
        if (hasBooks) {
            return "Bu kategoriye ait kitap var. Bu kategori silinemedi.";
        }
        categoryRepository.deleteById(id);
        return "Kategori silindi.";
    }
}
