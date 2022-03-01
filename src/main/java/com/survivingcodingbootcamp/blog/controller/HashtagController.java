package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.repository.HashtagRepository;
import com.survivingcodingbootcamp.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HashtagController {
    @Autowired
    private HashtagRepository hashtagRepo;
    @Autowired
    private PostRepository postRepo;

    @GetMapping("/hashtags")
    public String showAllHashtags(Model model) {
        List<Hashtag> hashtagList = (List<Hashtag>) hashtagRepo.findAll();
        model.addAttribute("hashtags", hashtagList);
        return "AllHashtagsTemplate";
    }

    @GetMapping("/hashtags/{id}")
    public String seeHashtag(Model model, @PathVariable long id) {
        Optional<Hashtag> hashtagOptional = hashtagRepo.findById(id);
        model.addAttribute("hashtag", hashtagOptional.get());
        return "single-hashtag-template";
    }

    @PostMapping("/posts/hashtags/{id}")
    public String addHashtag(@PathVariable Long id, @RequestParam String hashtag) {
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
        return "redirect:/posts/" + id;
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
