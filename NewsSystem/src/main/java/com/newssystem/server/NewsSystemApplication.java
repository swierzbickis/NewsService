package com.newssystem.server;

import com.newssystem.server.domain.Comment;
import com.newssystem.server.domain.News;
import com.newssystem.server.service.CommentService;
import com.newssystem.server.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan
public class NewsSystemApplication implements CommandLineRunner{

    @Autowired
    public NewsService newsService;

    @Autowired
    public CommentService commentService;

    public static void main(String[] args) {
        SpringApplication.run(NewsSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        News obj = new News(null,"Tytul","Tresc","23.12.2016");
        newsService.create(obj);

        Comment comment = new Comment("456786","Trescc","Autor","23.12.2016");
        commentService.create(comment);
    }
}
