package com.survivingcodingbootcamp.blog.service;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.repository.HashtagRepository;
import com.survivingcodingbootcamp.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class HashtagStorage {
    @Autowired
    private HashtagRepository hashtagRepo;
    @Autowired
    private PostRepository postRepo;

    public List<Hashtag> hashtagList(){
        return (List<Hashtag>) hashtagRepo.findAll();
    }

    public Hashtag getHashtag(long id){
        Optional<Hashtag> hashtagOptional = hashtagRepo.findById(id);
        return hashtagOptional.get();
    }

    public void saveHashtag(Long id, String hashtag) {
        String tempHash = checkHashTag(hashtag);
        Optional<Hashtag> hashtagOptional = hashtagRepo.findByHashtag(tempHash);
        Post post = postRepo.findById(id).get();

        if(hashtagOptional.isPresent()) {
            if(!hashtagOptional.get().getPosts().contains(post)) {
                hashtagOptional.get().setPost(post);
                hashtagRepo.save(hashtagOptional.get());
            }
        }
        else {
            Hashtag hashtag1 = new Hashtag(tempHash, post);
            hashtagRepo.save(hashtag1);
        }
    }
    private String checkHashTag(String hashtag) {
        String tempHashtag = "";
        if (hashtag.contains("#")) {
            tempHashtag = hashtag;
        } else {
            tempHashtag = "#" + hashtag;
        }
        return tempHashtag;
    }
}
