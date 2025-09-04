package com.back.service;

import com.back.constant.BoardType;
import com.back.dto.ImgCardDto;
import com.back.entity.Board;
import com.back.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<ImgCardDto> findAll() {
        List<ImgCardDto> imgCardDtos = new ArrayList<>();
        for (Board board : boardRepository.findAll()) {
            ImgCardDto imgCardDto = ImgCardDto.builder()
                    .id(board.getTable_id())
                    .title(board.getTitle())
                    .contents(board.getContent())
                    .img(board.getImg())
                    .type(board.getBoardtype())
                    .build();
            imgCardDtos.add(imgCardDto);
        }
        return imgCardDtos;
    }

    public List<ImgCardDto> findByType(BoardType type) {
        List<ImgCardDto> imgCardDtos = new ArrayList<>();
        for (Board board : boardRepository.findAllByBoardtype(type)) {
            ImgCardDto imgCardDto = ImgCardDto.builder()
                    .id(board.getTable_id())
                    .title(board.getTitle())
                    .contents(board.getContent())
                    .img(board.getImg())
                    .type(board.getBoardtype())
                    .build();
            imgCardDtos.add(imgCardDto);
        }
        return imgCardDtos;
    }

//    public Board
}
