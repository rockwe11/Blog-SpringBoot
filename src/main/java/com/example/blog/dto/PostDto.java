package com.example.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 3, max = 200)
    private String title;

    @NotBlank(message = "Content cannot be empty")
    private String content;

    private String status;
    private AuthorDto author;
    private List<CommentDto> comments;
    private List<TagDto> tags;
}
