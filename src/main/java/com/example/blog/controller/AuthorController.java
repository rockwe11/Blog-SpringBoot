package com.example.blog.controller;

import com.example.blog.dto.AuthorDto;
import com.example.blog.entity.Author;
import com.example.blog.mapper.AuthorMapper;
import com.example.blog.repository.AuthorRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorController(AuthorRepository authorRepository,
                            AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @GetMapping
    public List<AuthorDto> getAll() {
        return authorRepository.findAll().stream()
                .map(AuthorMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getById(@PathVariable Long id) {
        return authorRepository.findById(id)
                .map(a -> ResponseEntity.ok(authorMapper.toDto(a)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AuthorDto create(@Valid @RequestBody AuthorDto dto) {
        Author author = authorMapper.toEntity(dto);
        authorRepository.save(author);
        return authorMapper.toDto(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> update(@PathVariable Long id,
                                            @RequestBody AuthorDto dto) {
        return authorRepository.findById(id)
                .map(existing -> {
                    existing.setName(dto.getName());
                    existing.setEmail(dto.getEmail());
                    authorRepository.save(existing);
                    return ResponseEntity.ok(authorMapper.toDto(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!authorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        authorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
