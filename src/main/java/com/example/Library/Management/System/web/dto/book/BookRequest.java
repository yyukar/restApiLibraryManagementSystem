package com.example.Library.Management.System.web.dto.book;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.Set;

@Data
public class BookRequest {
    @NotBlank(message = "Kitap adı boş olamaz")
    private String name;

    @Min(value = 1400)
    private Integer publicationYear;

    @Min(value = 0)
    private Integer stock;

    @NotNull
    private Long authorId;

    @NotNull
    private Long publisherId;

    @NotNull
    private Set<Long> categoryIds;
}
