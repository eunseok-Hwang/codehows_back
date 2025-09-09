package com.back.repository;

import com.back.entity.FoodImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodImgRepository extends JpaRepository<FoodImg, Long> {

    // ID와 repImgYn을 기준으로 단일 이미지를 찾는 메서드
    // 메서드 이름을 findByIdAndRepImgYn으로 변경
    // 파라미터 이름도 id로 변경하는 것이 가독성에 좋습니다.
    Optional<FoodImg> findByIdAndRepImgYn(Long id, String repImgYn);


}