package com.back.controller;

import com.back.constant.BoardType;
import com.back.dto.BoardDto;
import com.back.dto.ImgCardDto;


import com.back.entity.Board;
import com.back.repository.BoardRepository;
import com.back.service.BoardService;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequiredArgsConstructor

@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;
    private final BoardRepository boardRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @GetMapping("/list/{type}")
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

//    @PostMapping("/post")
//    public void createBoard(@RequestBody BoardFormDto boardDto) {
//        boardService.saveBoard(boardDto);
//    }

    @PostMapping("/post")
    public ResponseEntity<?> createBoardWithImages(
            @RequestPart("boardDto") BoardDto boardDto,
            @RequestPart(name = "imageFiles", required = false) List<MultipartFile> imageFiles
    ) {
        try {
            Long boardId = boardService.saveBoardWithImages(boardDto, imageFiles);
            return ResponseEntity.ok(boardId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 등록 실패");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDto> findByTableId(@PathVariable Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다"));


        BoardDto dto = BoardDto.fromEntity(board);
        return ResponseEntity.ok(dto);

    }

    @DeleteMapping("/{board_id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long board_id){
        boardService.deleteBoard(board_id);
        return ResponseEntity.ok().body(board_id);
    }

}
