package com.example.Library.Management.System.web.dto.publisher;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PublisherRequest {
    @NotBlank(message = "Yayınevi adı boş olamaz")
    private String name;

    @Min(value = 1400, message = "Kuruluş yılı mantıklı bir değer olmalı")
    private Integer establishmentYear;

    @Size(min = 2, message = "Adres en az 2 karakter olmalı")
    private String address;
}
