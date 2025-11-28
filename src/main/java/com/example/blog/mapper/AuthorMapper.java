package com.example.blog.mapper;

import com.example.blog.dto.AuthorDto;
import com.example.blog.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public static AuthorDto toDto(Author a) {
        if (a == null) return null;

        return new AuthorDto(
                a.getId(),
                a.getName(),
                a.getEmail()
        );
    }

    public Author toEntity(AuthorDto dto) {
        if (dto == null) return null;

        Author a = new Author();
        a.setId(dto.getId());
        a.setName(dto.getName());
        a.setEmail(dto.getEmail());
        return a;
    }
}
