package com.back.repository;

import com.back.entity.Board;
import com.back.entity.Comment;
import com.back.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBoard(Board board);
}
