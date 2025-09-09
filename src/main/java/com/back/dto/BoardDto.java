package com.back.dto;

import com.back.constant.BoardType;
import com.back.entity.Board;
import com.back.entity.Member;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString

public class BoardDto {

    private String type;
    private Long id;
    private String title;

    private String contents;

    private String img;

    public static BoardDto fromEntity(Board board) {
        return BoardDto.builder()
                .id(board.getBoard_id())
                .title(board.getTitle())
                .contents(board.getContent())
                .img(board.getImg())
                .type(board.getBoardtype().name())
                .build();
    }

}
