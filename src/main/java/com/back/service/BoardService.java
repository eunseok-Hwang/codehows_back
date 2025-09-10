package com.back.service;

import com.back.constant.BoardType;
import com.back.dto.BoardDto;
import com.back.dto.ImgCardDto;
import com.back.entity.Board;
import com.back.entity.FoodImg;
import com.back.repository.BoardRepository;
import com.back.repository.CommentRepository;
import com.back.repository.FoodImgRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final FoodImgRepository foodImgRepository;
    private final FileService fileService;
    private String FoodImgLocation = "C:/codehows_back/temp/img";

    public List<ImgCardDto> findAll() {
        List<ImgCardDto> imgCardDtos = new ArrayList<>();
        for (Board board : boardRepository.findAll()) {
            ImgCardDto imgCardDto = ImgCardDto.builder()
                    .id(board.getBoard_id())
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
                    .id(board.getBoard_id())
                    .title(board.getTitle())
                    .contents(board.getContent())
                    .img(board.getImg())
                    .type(board.getBoardtype())
                    .build();
            imgCardDtos.add(imgCardDto);
        }
        return imgCardDtos;
    }

//    public Board saveBoard(BoardDto boardDto) {
//        Board board = Board.builder()
//                .content(boardDto.getContent())
//                .title(boardDto.getTitle())
//                .img(boardDto.getImg())
//                .boardtype(boardDto.getBoardtype())
//                .build();
//        boardRepository.save(board);
//        return board;
//    }
//
//    public void saveBoardImages(Long boardId, List<MultipartFile> imageFiles) throws Exception {
//        Board board = boardRepository.findById(boardId)
//                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다"));
//
//        for (int i = 0; i < imageFiles.size(); i++) {
//            MultipartFile file = imageFiles.get(i);
//            String savedFileName = fileService.uploadFile(file);
//
//            BoardImg boardImg = new BoardImg();
//            boardImg.setBoard(board);
//            boardImg.setImgName(savedFileName);
//            boardImg.setRepImgYn(i == 0 ? "Y" : "N");
//
//            boardImgRepository.save(boardImg);
//        }
//    }

    public Long saveBoardWithImages(BoardDto boardDto, List<MultipartFile> imageFiles) throws Exception {
        // 1. 게시글 엔티티를 먼저 생성하고 저장합니다.
        //    그래야 이미지 엔티티와 연결할 게시글의 ID가 생깁니다.
        BoardType boardType = BoardType.valueOf(boardDto.getType());

        Board board = boardRepository.save(Board.builder()
                .content(boardDto.getContents())
                .title(boardDto.getTitle())
                .boardtype(boardType)
                .build());

        if(imageFiles == null) return board.getBoard_id();
        // 2. 이미지 파일 리스트를 반복문으로 순회하며 각 파일을 처리합니다.
        for (int i = 0; i < imageFiles.size(); i++) {
            MultipartFile imgFile = imageFiles.get(i); // 리스트에서 개별 파일을 가져옵니다.

            // 빈 파일이라면 건너뜁니다.
            if (imgFile.isEmpty()) {
                continue;
            }

            // 3. 각 파일마다 새로운 FoodImg 엔티티를 생성합니다.
            FoodImg foodImg = new FoodImg();
            foodImg.setBoard(board); // 현재 게시글과 이미지 연결

            // 4. 반복문의 인덱스(i)를 이용해 첫 번째 이미지 여부를 판단합니다.
            String repImgYn = (i == 0) ? "Y" : "N";
            foodImg.setRepImgYn(repImgYn);

            // 5. 개별 파일을 업로드하고 엔티티에 파일 정보를 저장합니다.
            String orgFileName = imgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(FoodImgLocation, orgFileName, imgFile.getBytes());
            String imgUrl = "/images/" + imgName;

            foodImg.updateFoodImg(imgName, imgUrl, orgFileName);

            // 6. 첫 번째 이미지라면, 게시글 엔티티의 img 필드에 URL을 저장합니다.
            if (i == 0) {
                board.setImg(imgUrl);
            }

            // 7. FoodImg 엔티티를 데이터베이스에 저장합니다.
            foodImgRepository.save(foodImg);
        }

        // 최종적으로 저장된 게시글의 ID를 반환합니다.
        return board.getBoard_id();
    }

    public void deleteBoard(Long board_id){
        Board board = boardRepository.findById(board_id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));
        boardRepository.delete(board);
    }


}
