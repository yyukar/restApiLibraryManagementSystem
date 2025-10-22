package com.example.Library.Management.System.web.dto.book;

import lombok.Data;
import java.util.Set;

@Data
public class BookResponse {
    private Long id;
    private String name;
    private Integer publicationYear;
    private Integer stock;
    private Long authorId;
    private Long publisherId;
    private Set<Long> categoryIds;
}
