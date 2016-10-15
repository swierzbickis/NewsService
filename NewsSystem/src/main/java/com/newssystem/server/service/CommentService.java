package com.newssystem.server.service;

import com.newssystem.server.domain.Comment;
import com.newssystem.server.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;

import java.util.List;

/**
 * Created by Sebek on 2016-10-08.
 */
@Service
public class CommentService implements ServiceInterface<Comment>,CustomInterfaceComment {

    public CommentRepository commentRepository;

    public CommentService() {}

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getObj() {
        List<Comment> commentList = commentRepository.findAll();
        return convertToDTOs(commentList);
    }

    private List<Comment> convertToDTOs(List<Comment> models) {
        return models.stream().map(this::convertToDTO).collect(toList());
    }

    private Comment convertToDTO(Comment comment) {
        Comment dto = new Comment();
        dto.setComment(comment.getComment());
        dto.setId(comment.getId());
        dto.setData(comment.getData());
        dto.setAuthor(comment.getAuthor());
        dto.setNewsId(comment.getNewsId());
        return dto;
    }

    @Override
    public Comment create(Comment obj) {
        return commentRepository.save(obj);
    }

    @Override
    public Comment findById(String id) {
        return null;
    }

    @Override
    public Comment update(Comment obj) {
        return null;
    }

    @Override
    public List<Comment> findByNewsId(String id) {
        List<Comment> commentList = commentRepository.findByNewsId(id);
        return convertToDTOs(commentList);
    }
}
