package com.back.service;

import com.back.constant.BoardType;
import com.back.dto.BoardDto;
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
                    .id(board.getTableId())
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
                    .id(board.getTableId())
                    .title(board.getTitle())
                    .contents(board.getContent())
                    .img(board.getImg())
                    .type(board.getBoardtype())
                    .build();
            imgCardDtos.add(imgCardDto);
        }
        return imgCardDtos;
    }

    public Board saveBoard(BoardDto boardDto) {
        Board board = Board.builder()
                .content(boardDto.getContent())
                .title(boardDto.getTitle())
                .img(boardDto.getImg())
                .boardtype(boardDto.getBoardtype())
                .
                .build();
        boardRepository.save(board);
        return board;
    }


}
