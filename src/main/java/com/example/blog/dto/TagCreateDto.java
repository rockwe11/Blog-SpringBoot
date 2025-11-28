package com.example.blog.dto;

import jakarta.validation.constraints.NotBlank;

public record TagCreateDto(
        @NotBlank String name
) {}
