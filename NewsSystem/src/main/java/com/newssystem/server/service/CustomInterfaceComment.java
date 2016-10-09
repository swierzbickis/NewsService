package com.newssystem.server.service;

import com.newssystem.server.domain.Comment;

import java.util.List;

/**
 * Created by Sebek on 2016-10-08.
 */
public interface CustomInterfaceComment {

   List<Comment> findByNewsId(String id);

}
