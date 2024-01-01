package com.example.beecommerce.repository;

import com.example.beecommerce.pojo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Comment findCommentById(Long id);

    List<Comment> findAll();

    List<Comment> findCommentsByIdProduct(Long id);

}
