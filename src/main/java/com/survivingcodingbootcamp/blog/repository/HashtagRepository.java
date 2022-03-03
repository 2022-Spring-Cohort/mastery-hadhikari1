package com.survivingcodingbootcamp.blog.repository;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface HashtagRepository extends CrudRepository<Hashtag, Long> {
    Optional<Hashtag> findByHashtag(String hashtag);
}
