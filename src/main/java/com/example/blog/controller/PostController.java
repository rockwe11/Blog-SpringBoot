package com.example.blog.controller;

import com.example.blog.dto.PostDto;
import com.example.blog.entity.Post;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.mapper.PostMapper;
import com.example.blog.repository.AuthorRepository;
import com.example.blog.repository.PostRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepo;
    private final AuthorRepository authorRepo;

    @PostMapping
    public PostDto create(@Valid @RequestBody Post dto) {
        Post saved = postRepo.save(dto);
        return PostMapper.toDto(saved);
    }

    @PutMapping("/{id}")
    public PostDto update(@PathVariable Long id, @RequestBody Post dto) {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setStatus(dto.getStatus());

        Post saved = postRepo.save(post);
        return PostMapper.toDto(saved);
    }

    @GetMapping
    public List<PostDto> all() {
        return postRepo.findAll()
                .stream()
                .map(PostMapper::toDto)
                .collect(Collectors.toList());
    }
}

