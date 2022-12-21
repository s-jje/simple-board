package com.project.simpleboard.repository;

import com.project.simpleboard.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBoardIdOrderByCreatedAtDesc(Long boardId);

}
