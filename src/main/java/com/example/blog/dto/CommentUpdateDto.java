package com.example.blog.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentUpdateDto(
        @NotBlank String content,
        boolean approved
) {}
