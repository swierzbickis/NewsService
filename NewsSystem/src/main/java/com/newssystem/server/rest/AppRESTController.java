package com.newssystem.server.rest;

import com.newssystem.server.domain.Comment;
import com.newssystem.server.domain.News;
import com.newssystem.server.service.CommentService;
import com.newssystem.server.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Sebek on 2016-10-08.
 */
@RestController
@RequestMapping("/api/news")
public class AppRESTController {

    private final CommentService commentService;
    private final NewsService newsService;

    @Autowired
    public AppRESTController(CommentService commentService, NewsService newsService) {
        this.commentService = commentService;
        this.newsService = newsService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getNews")
    public
    @ResponseBody
    List<News> findAll() {
        return newsService.getObj();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getComments")
    public
    @ResponseBody
    List<Comment> findAllComment() {
        return commentService.getObj();
    }


    @RequestMapping(method = RequestMethod.POST , value = "/saveNews")
    public
    @ResponseBody
    News create(@RequestBody News newsEntity){
        return newsService.create(newsEntity);
    }

    @RequestMapping(method = RequestMethod.POST , value = "/saveComment")
    public
    @ResponseBody
    Comment create(@RequestBody Comment commentEntity){
        return commentService.create(commentEntity);
    }



}
