package com.spring.springdeep01.repository;

import com.spring.springdeep01.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Override
    Optional<Comment> findById(Long id);
}
