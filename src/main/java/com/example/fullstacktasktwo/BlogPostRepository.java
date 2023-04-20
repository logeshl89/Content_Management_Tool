package com.example.fullstacktasktwo;

import com.example.fullstacktasktwo.Entiy.BlogPost;

import java.util.List;

public interface BlogPostRepository {
    List<BlogPost> findAll();
    BlogPost findById(Long id);
    BlogPost save(BlogPost blogPost);
    void delete(BlogPost blogPost);
}

