package com.example.blog.dto;

import java.util.List;
import java.util.Set;

public record PostDto(
        Long id,
        String title,
        String content,
        String status,
        AuthorDto author,
        List<CommentDto> comments,
        Set<TagDto> tags
) {}
