package com.example.blog.mapper;

import com.example.blog.dto.*;
import com.example.blog.entity.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class PostMapper {

    public static PostDto toDto(Post p) {
        if (p == null) return null;

        return new PostDto(
                p.getId(),
                p.getTitle(),
                p.getContent(),
                p.getStatus(),
                AuthorMapper.toDto(p.getAuthor()),
                p.getComments() == null ? null :
                        p.getComments().stream()
                                .map(CommentMapper::toDto)
                                .toList(),
                p.getTags() == null ? null :
                        p.getTags().stream()
                                .map(TagMapper::toDto)
                                .collect(Collectors.toSet())
        );
    }

    public static Post toEntity(PostCreateDto dto, Author author, Set<Tag> tags) {
        Post p = new Post();
        p.setTitle(dto.title());
        p.setContent(dto.content());
        p.setStatus(dto.status());
        p.setAuthor(author);
        p.setTags(tags);
        p.setCreatedAt(LocalDateTime.now());
        return p;
    }

    public static void updateEntity(Post p, PostUpdateDto dto, Author author, Set<Tag> tags) {
        p.setTitle(dto.title());
        p.setContent(dto.content());
        p.setStatus(dto.status());
        p.setAuthor(author);
        p.setTags(tags);
    }
}
