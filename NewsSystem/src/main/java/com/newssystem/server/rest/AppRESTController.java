package com.newssystem.server.rest;

import com.newssystem.server.domain.Comment;
import com.newssystem.server.domain.News;
import com.newssystem.server.service.CommentService;
import com.newssystem.server.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Sebek on 2016-10-08.
 */
@RestController
@RequestMapping("/api/news")
public class AppRESTController {

    private final CommentService commentService;
    private final NewsService newsService;
    private final Map<String, Object> response = new LinkedHashMap<>();


    @Autowired
    public AppRESTController(CommentService commentService, NewsService newsService) {
        this.commentService = commentService;
        this.newsService = newsService;
    }



    @CrossOrigin(value = "*")
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


    @CrossOrigin(value = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/getNewsById/{id}")
    public @ResponseBody News findById(@PathVariable String id){
        News news = newsService.findById(id);
        return news;
    }

    @CrossOrigin(value = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/getCommentsByNewsId/{id}")
    public @ResponseBody List<Comment> findByNewsId(@PathVariable String id){
        return commentService.findByNewsId(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveNews")
    public
    @ResponseBody
    Map<String, Object> create(@Valid @RequestBody News newsEntity, BindingResult bindingResult) {

        if(checkError(bindingResult)){
            newsService.create(newsEntity);
            response.put("message", "News created successfully");
        }

        return response;
    }
    @CrossOrigin(value = "*")
    @RequestMapping(method = RequestMethod.POST, value = "/getCommentsByNewsId/{id}")
    public
    @ResponseBody
    Map<String,Object> create(@Valid @RequestBody Comment commentEntity,BindingResult bindingResult,@PathVariable String id) {

        if(checkError(bindingResult)){
            commentService.create(commentEntity);
            response.put("message", "Comment created successfully");
        }

        return response;
    }

    public Boolean checkError(BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            response.put("message", "Error");
            for (FieldError error : errors) {
                response.put(error.getField(), error.getDefaultMessage());
            }
            return false;
        } else {
            return true;
        }
    }
}
