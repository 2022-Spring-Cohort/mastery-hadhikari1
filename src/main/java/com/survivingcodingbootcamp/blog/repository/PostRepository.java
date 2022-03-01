package com.survivingcodingbootcamp.blog.repository;

import com.survivingcodingbootcamp.blog.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface PostRepository extends CrudRepository<Post, Long> {
}
