package com.example.Library.Management.System.repository;

import com.example.Library.Management.System.domain.entity.BookBorrowing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookBorrowingRepository extends JpaRepository<BookBorrowing, Long> {}

