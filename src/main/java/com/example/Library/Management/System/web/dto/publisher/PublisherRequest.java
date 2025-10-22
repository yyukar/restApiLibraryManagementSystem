package com.example.Library.Management.System.web.dto.publisher;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PublisherRequest {
    @NotBlank(message = "Yayınevi adı boş olamaz")
    private String name;

    @Min(value = 1400, message = "Kuruluş yılı mantıklı bir değer olmalı")
    private Integer establishmentYear;

    @NotBlank(message = "Adres boş olamaz")
    private String address;
}
