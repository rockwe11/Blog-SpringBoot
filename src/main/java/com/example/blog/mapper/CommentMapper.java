package com.example.blog.mapper;

import com.example.blog.dto.*;
import com.example.blog.entity.*;

public class CommentMapper {

    public static CommentDto toDto(Comment c) {
        if (c == null) return null;

        return new CommentDto(
                c.getId(),
                c.getContent(),
                c.isApproved(),
                AuthorMapper.toDto(c.getAuthor())
        );
    }

    public static Comment toEntity(CommentCreateDto dto, Author author, Post post) {
        Comment c = new Comment();
        c.setContent(dto.content());
        c.setAuthor(author);
        c.setPost(post);
        c.setApproved(false);
        return c;
    }

    public static void updateEntity(Comment c, CommentUpdateDto dto) {
        c.setContent(dto.content());
        c.setApproved(dto.approved());
    }
}
