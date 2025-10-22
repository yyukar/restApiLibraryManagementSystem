package com.example.Library.Management.System.repository;

import com.example.Library.Management.System.domain.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {}

