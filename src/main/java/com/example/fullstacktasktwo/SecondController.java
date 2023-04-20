package com.example.fullstacktasktwo;

import com.example.fullstacktasktwo.Entiy.BlogPost;
import com.example.fullstacktasktwo.Entiy.RepositoriesBlogPost;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Base64;
import java.util.List;

@Controller
public class SecondController {
    private final RepositoriesBlogPost repositoriesBlogPost;


    public SecondController(RepositoriesBlogPost repositoriesBlogPost) {
        this.repositoriesBlogPost = repositoriesBlogPost;
    }
    @RequestMapping("/GetData")
    String ShowData(Model model)
    {
        System.out.println("called");
        List<BlogPost> blogPosts = repositoriesBlogPost.findAll();
        for (BlogPost blogPost : blogPosts) {
            String base64Image = Base64.getEncoder().encodeToString(blogPost.getFeaturedImage());
            blogPost.setBase64Image(base64Image);
        }
        System.out.println(blogPosts);
        model.addAttribute("posts", blogPosts);
        return "index";
    }
}
