package com.example.blog.controller;

import com.example.blog.dto.CommentDto;
import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.mapper.CommentMapper;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository repo;
    private final PostRepository postRepo;

    @PostMapping("/post/{postId}")
    public CommentDto create(@Valid @PathVariable Long postId, @RequestBody Comment c) {

        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        c.setPost(post);

        return CommentMapper.toDto(repo.save(c));
    }

    @GetMapping
    public List<CommentDto> all() {
        return repo.findAll()
                .stream()
                .map(CommentMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CommentDto get(@PathVariable Long id) {
        return CommentMapper.toDto(repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment not found")));
    }

    @PutMapping("/{id}")
    public CommentDto update(@PathVariable Long id, @RequestBody Comment c) {
        Comment existing = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        existing.setContent(c.getContent());
        existing.setApproved(c.getApproved());
        existing.setAuthor(c.getAuthor());

        return CommentMapper.toDto(repo.save(existing));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
