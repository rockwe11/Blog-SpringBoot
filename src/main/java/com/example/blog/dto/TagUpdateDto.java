package com.example.blog.dto;

import jakarta.validation.constraints.NotBlank;

public record TagUpdateDto(
        @NotBlank String name
) {}
