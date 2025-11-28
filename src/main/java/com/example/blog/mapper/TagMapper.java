package com.example.blog.mapper;

import com.example.blog.dto.TagDto;
import com.example.blog.entity.Tag;

public class TagMapper {

    public static TagDto toDto(Tag t) {
        if (t == null) return null;
        return new TagDto(
                t.getId(),
                t.getName()
        );
    }
}
