package com.example.blog.controller;

import com.example.blog.dto.*;
import com.example.blog.entity.*;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.mapper.PostMapper;
import com.example.blog.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepo;
    private final AuthorRepository authorRepo;
    private final TagRepository tagRepo;

    @PostMapping
    public PostDto create(@Valid @RequestBody PostCreateDto dto) {

        Author author = authorRepo.findById(dto.authorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

//        Set<Tag> tags = dto.tagIds() == null ? Set.of() :
//                new HashSet<>(tagRepo.findAllById(dto.tagIds()));

        Set<Tag> tags = Collections.emptySet();
        if (dto.tags() != null && !dto.tags().isEmpty()) {
            List<Tag> foundTags = tagRepo.findAllById(dto.tags());

            if (foundTags.size() != dto.tags().size()) {
                List<Long> foundIds = foundTags.stream().map(Tag::getId).toList();
                List<Long> missingIds = dto.tags().stream()
                        .filter(id -> !foundIds.contains(id))
                        .toList();
                throw new ResourceNotFoundException("Tags not found with ids: " + missingIds);
            }

            tags = new HashSet<>(foundTags);
        }

        Post p = PostMapper.toEntity(dto, author, tags);

        return PostMapper.toDto(postRepo.save(p));
    }

    @PutMapping("/{id}")
    public PostDto update(
            @PathVariable Long id,
            @Valid @RequestBody PostUpdateDto dto
    ) {
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        Author author = authorRepo.findById(dto.authorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        Set<Tag> tags = dto.tagIds() == null ? Set.of() :
                new HashSet<>(tagRepo.findAllById(dto.tagIds()));

        PostMapper.updateEntity(post, dto, author, tags);

        return PostMapper.toDto(postRepo.save(post));
    }

    @GetMapping
    public List<PostDto> all() {
        return postRepo.findAll().stream()
                .map(PostMapper::toDto)
                .toList();
    }
}
