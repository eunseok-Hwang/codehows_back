package com.back.dto;

import com.back.constant.BoardType;
import com.back.entity.Board;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Setter
@Getter
public class BoardFormDto {
    private String title;
    private String content;
    private String img;
    private BoardType boardType;
    private Long writerId;

    private static ModelMapper modelMapper = new ModelMapper();

    public Board createBoard() {
        return modelMapper.map(this, Board.class);
    }
}
