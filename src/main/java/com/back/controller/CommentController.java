package com.back.controller;

import com.back.dto.CommentDto;
import com.back.entity.Board;
import com.back.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{boardId}/comments")
    public ResponseEntity<?> getCommentsByBoardId(@PathVariable Long boardId) {
        return ResponseEntity.ok(commentService.getCommentsByBoardId(boardId));
    }

    @PostMapping("/{boardId}/comments")
    public ResponseEntity<?> getCommentsByBoardId(
            @PathVariable Long boardId,
            @RequestBody CommentDto commentDto,
            Authentication authentication
    ) {

        commentService.createComment(
                boardId,
                commentDto.getComment(),
                authentication.getName());

        // 댓글 목록을 성공적으로 반환
        return ResponseEntity.ok().build();
    }
}
