package com.newssystem.server.repository;

import com.newssystem.server.domain.News;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Sebek on 2016-10-01.
 */
@Repository
public interface NewsRepository extends MongoRepository<News,String>{

    List<News> findAllByOrderByDataDesc();

}
