package com.example.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentCreateDto(
        @NotBlank String content,
        @NotNull Long authorId,
        @NotNull Long postId
) {}
