package com.example.blog.mapper;

import com.example.blog.dto.AuthorDto;
import com.example.blog.entity.Author;

public class AuthorMapper {

    public static AuthorDto toDto(Author a) {
        if (a == null) return null;
        return new AuthorDto(
                a.getId(),
                a.getName(),
                a.getEmail()
        );
    }
}
