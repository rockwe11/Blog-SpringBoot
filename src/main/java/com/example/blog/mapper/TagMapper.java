package com.example.blog.mapper;

import com.example.blog.dto.*;
import com.example.blog.entity.Tag;

public class TagMapper {

    public static TagDto toDto(Tag t) {
        if (t == null) return null;

        return new TagDto(
                t.getId(),
                t.getName()
        );
    }

    public static Tag toEntity(TagCreateDto dto) {
        Tag t = new Tag();
        t.setName(dto.name());
        return t;
    }

    public static void updateEntity(Tag tag, TagUpdateDto dto) {
        tag.setName(dto.name());
    }
}
