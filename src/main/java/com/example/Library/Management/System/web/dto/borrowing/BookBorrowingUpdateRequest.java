package com.example.Library.Management.System.web.dto.borrowing;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BookBorrowingUpdateRequest {
    @NotNull
    private Long bookId;

    @NotBlank
    private String borrowerName;
}
