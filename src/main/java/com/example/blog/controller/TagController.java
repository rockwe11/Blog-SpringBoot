package com.example.blog.controller;

import com.example.blog.dto.TagDto;
import com.example.blog.entity.Tag;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.mapper.TagMapper;
import com.example.blog.repository.TagRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagRepository tagRepo;

    @PostMapping
    public TagDto create(@Valid @RequestBody Tag t) {
        return TagMapper.toDto(tagRepo.save(t));
    }

    @GetMapping
    public List<TagDto> all() {
        return tagRepo.findAll()
                .stream()
                .map(TagMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TagDto get(@PathVariable Long id) {
        return TagMapper.toDto(tagRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tag not found")));
    }

    @PutMapping("/{id}")
    public TagDto update(@PathVariable Long id, @RequestBody Tag t) {
        Tag existing = tagRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tag not found"));
        existing.setName(t.getName());
        return TagMapper.toDto(tagRepo.save(existing));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tagRepo.deleteById(id);
    }
}
