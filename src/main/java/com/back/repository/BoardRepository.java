package com.back.repository;

import com.back.constant.BoardType;
import com.back.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByBoardtype(BoardType type);


}
