package com.example.Library.Management.System.web.dto.category;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotBlank(message = "Kategori adı boş olamaz")
    private String name;

    @Size(max = 255, message = "Açıklama 255 karakteri geçmemeli")
    private String description;
}
