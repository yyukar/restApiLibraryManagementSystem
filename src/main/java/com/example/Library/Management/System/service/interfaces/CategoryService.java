package com.example.Library.Management.System.service.interfaces;

import com.example.Library.Management.System.web.dto.category.CategoryRequest;
import com.example.Library.Management.System.web.dto.category.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse create(CategoryRequest req);
    CategoryResponse get(Long id);
    List<CategoryResponse> list();
    CategoryResponse update(Long id, CategoryRequest req);
    String delete(Long id);
}
