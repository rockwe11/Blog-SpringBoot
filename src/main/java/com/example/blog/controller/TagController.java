package com.example.blog.controller;

import com.example.blog.dto.*;
import com.example.blog.entity.Tag;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.mapper.TagMapper;
import com.example.blog.repository.TagRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagRepository tagRepo;

    @PostMapping
    public TagDto create(@Valid @RequestBody TagCreateDto dto) {
        Tag saved = tagRepo.save(TagMapper.toEntity(dto));
        return TagMapper.toDto(saved);
    }

    @PutMapping("/{id}")
    public TagDto update(
            @PathVariable Long id,
            @Valid @RequestBody TagUpdateDto dto
    ) {
        Tag tag = tagRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tag not found"));

        TagMapper.updateEntity(tag, dto);

        return TagMapper.toDto(tagRepo.save(tag));
    }

    @GetMapping
    public List<TagDto> all() {
        return tagRepo.findAll().stream()
                .map(TagMapper::toDto)
                .toList();
    }
}
