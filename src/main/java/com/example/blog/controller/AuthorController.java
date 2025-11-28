package com.example.blog.controller;

import com.example.blog.dto.AuthorDto;
import com.example.blog.entity.Author;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.mapper.AuthorMapper;
import com.example.blog.repository.AuthorRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepo;

    @PostMapping
    public AuthorDto create(@Valid @RequestBody Author a) {
        return AuthorMapper.toDto(authorRepo.save(a));
    }

    @GetMapping
    public List<AuthorDto> all() {
        return authorRepo.findAll()
                .stream()
                .map(AuthorMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AuthorDto get(@PathVariable Long id) {
        return AuthorMapper.toDto(authorRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found")));
    }

    @PutMapping("/{id}")
    public AuthorDto update(@PathVariable Long id, @RequestBody Author a) {
        Author existing = authorRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        existing.setName(a.getName());
        existing.setEmail(a.getEmail());
        return AuthorMapper.toDto(authorRepo.save(existing));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authorRepo.deleteById(id);
    }
}
