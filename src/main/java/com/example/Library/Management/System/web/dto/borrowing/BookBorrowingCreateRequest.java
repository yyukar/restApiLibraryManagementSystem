package com.example.Library.Management.System.web.dto.borrowing;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BookBorrowingCreateRequest {
    @NotNull
    private Long bookId;

    @NotBlank
    private String borrowerName;

    @Email
    @NotBlank
    private String borrowerEmail; // allowed only here

    @NotNull
    private LocalDate borrowingDate;
}
