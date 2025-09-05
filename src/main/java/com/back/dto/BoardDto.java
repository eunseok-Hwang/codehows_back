package com.back.dto;

import com.back.constant.BoardType;
import com.back.entity.Member;
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
    private BoardType boardtype;

    private String title;

    private String content;

    private String img;

    private LocalDateTime createdDate;
}
