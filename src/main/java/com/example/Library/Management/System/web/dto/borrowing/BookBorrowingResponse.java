package com.example.Library.Management.System.web.dto.borrowing;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BookBorrowingResponse {
    private Long id;
    private Long bookId;
    private String borrowerName;
    private String borrowerEmail;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
}
