package com.example.blog.mapper;

import com.example.blog.dto.PostDto;
import com.example.blog.entity.Post;

import java.util.stream.Collectors;

public class PostMapper {

    public static PostDto toDto(Post p) {
        if (p == null) return null;

        return new PostDto(
                p.getId(),
                p.getTitle(),
                p.getContent(),
                p.getStatus() != null ? p.getStatus().name() : null,
                AuthorMapper.toDto(p.getAuthor()),
                p.getComments() == null ? null :
                        p.getComments().stream()
                                .map(CommentMapper::toDto)
                                .collect(Collectors.toList()),
                p.getTags() == null ? null :
                        p.getTags().stream()
                                .map(TagMapper::toDto)
                                .collect(Collectors.toList())
        );
    }
}
