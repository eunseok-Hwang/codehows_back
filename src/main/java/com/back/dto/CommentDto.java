package com.back.dto;

import com.back.entity.Board;
import com.back.entity.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentDto {
    private String comment;
    private String nickname;
    private Long board_id;
}
