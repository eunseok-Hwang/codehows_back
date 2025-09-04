package com.back;

import com.back.constant.BoardType;
import com.back.entity.Board;
import com.back.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class BackApplication implements CommandLineRunner {

    private final BoardRepository boardRepository;

    public static void main(String[] args) {
        SpringApplication.run(BackApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        boardRepository.save(Board.builder()
                .title("차돌짬뽕")
                .content("맛있는 차짬")
                .img("chadol.jpg")
                .boardtype(BoardType.CN)
                .build());
        boardRepository.save(Board.builder()
                .title("김치찌개")
                .content("한국음식")
                .img("kimz.jpg")
                .boardtype(BoardType.KR)
                .build());
        boardRepository.save(Board.builder()
                .title("슈니첼")
                .content("독일음식")
                .img("suz.jpg")
                .boardtype(BoardType.WEST)
                .build());
        boardRepository.save(Board.builder()
                .title("오차즈케")
                .content("일본음식")
                .img("ohcha.jpg")
                .boardtype(BoardType.JP)
                .build());

    }
}
