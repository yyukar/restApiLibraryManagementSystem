package com.example.Library.Management.System.web.dto.borrowing;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BookBorrowingUpdateRequest {
    // only fields allowed to update; email intentionally not present
    private String borrowerName; // optional update if needed

    // Special endpoint exists for returning a book; but allow optional here
    private LocalDate returnDate;
}
