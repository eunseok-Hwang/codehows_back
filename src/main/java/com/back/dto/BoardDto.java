package com.back.dto;

import com.back.constant.BoardType;
import com.back.entity.Member;


public class BoardDto {
    private Long table_id;

    private BoardType boardtype;

    private String title;

    private String content;

    private Member writer;

    private String img;
}
