package com.example.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

public record PostCreateDto(
        @NotBlank String title,
        @NotBlank String content,
        @NotBlank String status,
        @NotNull Long authorId,
        Set<Long> tags
) {}
