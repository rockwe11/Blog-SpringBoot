package com.example.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private AuthorDto author;

    @NotBlank(message = "Content cannot be empty")
    private String content;
    private Boolean approved;
}
