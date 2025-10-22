package com.example.Library.Management.System.web.dto.author;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class AuthorRequest {
    @NotBlank(message = "Yazar adı boş olamaz")
    private String name;

    @Past(message = "Doğum tarihi geçmişte olmalı")
    private LocalDate birthDate;

    @NotBlank(message = "Ülke boş olamaz")
    private String country;
}
