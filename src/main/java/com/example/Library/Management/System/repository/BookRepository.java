package com.example.Library.Management.System.repository;

import com.example.Library.Management.System.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select count(b) > 0 from Book b join b.categories c where c.id = :categoryId")
    boolean existsByCategoryId(Long categoryId);
}
