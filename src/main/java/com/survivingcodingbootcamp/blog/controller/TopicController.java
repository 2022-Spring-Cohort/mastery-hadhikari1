package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.model.Topic;
import com.survivingcodingbootcamp.blog.repository.PostRepository;
import com.survivingcodingbootcamp.blog.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/topics")
public class TopicController {

    private TopicRepository topicRepo;

    @Autowired
    private PostRepository postRepo;

    public TopicController(TopicRepository topicRepo) {
        this.topicRepo = topicRepo;
    }

    @PostMapping("/")
    public String addTopics(@RequestParam String name) {
        Topic topic = new Topic(name);
        topicRepo.save(topic);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String displaySingleTopic(@PathVariable long id, Model model) {
        model.addAttribute("topic", topicRepo.findById(id).get());
        return "single-topic-template";
    }

    @PostMapping("/{id}")
    public String addPosts(@PathVariable Long id, @RequestParam String title, @RequestParam String author,
                           @RequestParam String content) {
        Topic topic = topicRepo.findById(id).get();
        Post post = new Post(title, author, topic, content);
        postRepo.save(post);
        return "redirect:/topics/" + id;
    }

}
