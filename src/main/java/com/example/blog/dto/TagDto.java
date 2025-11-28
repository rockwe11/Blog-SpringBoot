package com.example.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDto {
    private Long id;

    @NotBlank(message = "Tag name cannot be empty")
    @Size(min = 2, max = 30, message = "Length must be 2–30 characters")
    private String name;
}
