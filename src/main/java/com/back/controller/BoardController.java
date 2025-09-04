package com.back.controller;

import com.back.constant.BoardType;
import com.back.dto.ImgCardDto;


import com.back.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/{type}")
    public List<ImgCardDto> findAll(@PathVariable String type) {
        if(type.equals("ALL")) {
            return boardService.findAll();
        }else if(type.equals("CN")) {
            return boardService.findByType(BoardType.CN);
        }else if(type.equals("JP")) {
            return boardService.findByType(BoardType.JP);
        }else if(type.equals("KR")) {
            return boardService.findByType(BoardType.KR);
        }else if(type.equals("WEST")) {
            return boardService.findByType(BoardType.WEST);
        }
        return boardService.findAll();
    }
//
//    @PostMapping("/post")
//    public List
}
