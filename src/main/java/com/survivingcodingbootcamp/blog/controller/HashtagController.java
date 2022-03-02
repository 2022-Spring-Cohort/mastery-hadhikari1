package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.repository.HashtagRepository;
import com.survivingcodingbootcamp.blog.repository.PostRepository;
import com.survivingcodingbootcamp.blog.service.HashtagStorage;
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
    private HashtagStorage hashtagStorage;

    @GetMapping("/hashtags")
    public String showAllHashtags(Model model) {
        List<Hashtag> hashtagList = hashtagStorage.hashtagList();
        model.addAttribute("hashtags", hashtagList);
        return "AllHashtagsTemplate";
    }

    @GetMapping("/hashtags/{id}")
    public String seeHashtag(Model model, @PathVariable long id) {
       Hashtag hashtag = hashtagStorage.getHashtag(id);
        model.addAttribute("hashtag", hashtag);
        return "single-hashtag-template";
    }

    @PostMapping("/posts/hashtags/{id}")
    public String addHashtag(@PathVariable Long id, @RequestParam String hashtag) {
        hashtagStorage.saveHashtag(id, hashtag);
        return "redirect:/posts/" + id;
    }
}
