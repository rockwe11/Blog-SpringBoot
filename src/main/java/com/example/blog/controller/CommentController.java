package com.example.blog.controller;

import com.example.blog.dto.*;
import com.example.blog.entity.*;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.mapper.CommentMapper;
import com.example.blog.repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepo;
    private final AuthorRepository authorRepo;
    private final PostRepository postRepo;

    @PostMapping
    public CommentDto create(@Valid @RequestBody CommentCreateDto dto) {

        Author author = authorRepo.findById(dto.authorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        Post post = postRepo.findById(dto.postId())
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        Comment c = CommentMapper.toEntity(dto, author, post);

        return CommentMapper.toDto(commentRepo.save(c));
    }

    @PutMapping("/{id}")
    public CommentDto update(
            @PathVariable Long id,
            @Valid @RequestBody CommentUpdateDto dto
    ) {
        Comment c = commentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        CommentMapper.updateEntity(c, dto);

        return CommentMapper.toDto(commentRepo.save(c));
    }

    @GetMapping
    public List<CommentDto> all() {
        return commentRepo.findAll().stream()
                .map(CommentMapper::toDto)
                .toList();
    }
}
