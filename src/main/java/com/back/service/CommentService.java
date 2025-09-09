package com.back.service;

import com.back.constant.BoardType;
import com.back.dto.CommentDto;
import com.back.entity.Board;
import com.back.entity.Comment;
import com.back.entity.Member;
import com.back.repository.BoardRepository;
import com.back.repository.CommentRepository;
import com.back.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public void createComment(Long boardId, String comment, String username) {

        Board board = boardRepository.findById(boardId).orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByUserId(username).orElseThrow(EntityNotFoundException::new);
        Comment commentE = Comment.builder()
                .comment(comment)
                .board(board)
                .memberId(member)
                .build();
        commentRepository.save(commentE);
    }



    public List<CommentDto> getCommentsByBoardId(Long board_id) {
        Board board = boardRepository.findById(board_id).orElseThrow(EntityNotFoundException::new);
        List<Comment> list = commentRepository.findAllByBoard(board);
        List<CommentDto> dtoList = new ArrayList<>();
        for (Comment comment : list) {
            CommentDto commentDto = CommentDto.builder()
                    .comment(comment.getComment())
                    .nickname(comment.getMemberId().getNickname())
                    .board_id(board_id)
                    .build();
            dtoList.add(commentDto);
        }
        return dtoList;
    }
}
