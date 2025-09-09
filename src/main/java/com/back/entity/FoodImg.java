package com.back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "foodimg")
@Getter
@Setter
public class FoodImg extends BaseEntity {
    @Id
    @Column(name = "foodImg")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String imgName; // 서버 저장 파일명
    private String imgUrl;
    private String orgFileName; // 원본 파일명
    private String repImgYn;

    public void updateFoodImg(String imgName, String imgUrl, String orgFileName) {
        this.imgName = imgName;
        this.imgUrl = imgUrl;
        this.orgFileName = orgFileName;
    }
}
