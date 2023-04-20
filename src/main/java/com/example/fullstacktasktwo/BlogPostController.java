package com.example.fullstacktasktwo;

import com.example.fullstacktasktwo.Entiy.BlogPost;
import com.example.fullstacktasktwo.Entiy.RepositoriesBlogPost;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/posts")
public class BlogPostController {
    private final RepositoriesBlogPost repositoriesBlogPost;

    public BlogPostController(RepositoriesBlogPost repositoriesBlogPost) {
        this.repositoriesBlogPost = repositoriesBlogPost;
    }
    @GetMapping("/home")

    String showIndex(Model model)
    {
        List<BlogPost> blogPosts = repositoriesBlogPost.findAll();
        for (BlogPost blogPost : blogPosts) {
            String base64Image = Base64.getEncoder().encodeToString(blogPost.getFeaturedImage());
            blogPost.setBase64Image(base64Image);
        }
        System.out.println(blogPosts);
        model.addAttribute("posts", blogPosts);
        return "index";
    }
    @GetMapping("/NewUser")
    public String Show()
    {
        return "NewVlog";
    }
    @SneakyThrows
    @PostMapping("/create")
    public String createBlogPost(@RequestParam("title") String title,
                                 @RequestParam("body") String body,
                                 @RequestParam(value = "tags", required = false) String tags,
                                 @RequestParam("featured-image-file") MultipartFile featuredImageFile,
                                 @RequestParam("author") String author,
                                 @RequestParam("publish-date") LocalDateTime publishDate) {

        BlogPost blogPost = new BlogPost();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(featuredImageFile.getOriginalFilename()));
        if (fileName.contains("..")) {
            System.out.println("File does not exit");
        }
        blogPost.setTitle(title);
        blogPost.setTags(tags);
        blogPost.setAuthor(author);
        blogPost.setBody(body);
        blogPost.setTags(tags);
        blogPost.setPublishDate(publishDate);
        blogPost.setFeaturedImage(Base64.getEncoder().encodeToString(featuredImageFile.getBytes()).getBytes());
        repositoriesBlogPost.save(blogPost);
        return "redirect:/GetData";
    }
}
