package com.example.blog.dto;

public record CommentDto(
        Long id,
        String content,
        boolean approved,
        AuthorDto author
) {}
