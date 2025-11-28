package com.example.blog.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @Lob
    @NotBlank
    private String content;

    @Enumerated(EnumType.STRING)
    private PostStatus status = PostStatus.DRAFT;

    @ManyToOne
    private Author author;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany
    private Set<Tag> tags = new HashSet<>();
}

