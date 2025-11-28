package com.example.blog.mapper;

import com.example.blog.dto.CommentDto;
import com.example.blog.entity.Comment;

public class CommentMapper {

    public static CommentDto toDto(Comment c) {
        if (c == null) return null;

        return new CommentDto(
                c.getId(),
                AuthorMapper.toDto(c.getAuthor()),
                c.getContent(),
                c.getApproved()
        );
    }
}
