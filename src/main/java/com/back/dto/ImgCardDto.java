package com.back.dto;

import com.back.constant.BoardType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ImgCardDto {
    private Long id;
    private String title;
    private String contents;
    private String img;
    private BoardType type;
}
