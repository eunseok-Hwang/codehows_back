package com.back.service;

import com.back.entity.FoodImg;
import com.back.repository.FoodImgRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodImgService {
    @Value("${UploadPath}")
    private String FoodImgLocation;

    private final FoodImgRepository foodImgRepository;

    private final FileService fileService;

    public void saveFoodImg(FoodImg foodImg, MultipartFile foodImgFile) throws Exception {
        String orgFileName = foodImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        // 파일 시스템에 파일 업로드
        if(StringUtils.hasText(orgFileName)){
            imgName = fileService.uploadFile(FoodImgLocation, orgFileName, foodImgFile.getBytes());
            imgUrl = "/images/item/" + imgName;
        }

        foodImg.updateFoodImg(orgFileName, imgName, imgUrl);
        // DB에 저장된 파일 정보 저장
        foodImgRepository.save(foodImg);
    }

    public void updateFoodImg(Long foodImgId, MultipartFile foodImgFile, String repImgYn) throws Exception
    {
        if (!foodImgFile.isEmpty()){
            // FoodImg 엔티티의 ID 필드명(`id`)과 RepImgYn을 사용하도록 수정
            FoodImg existingFoodImg = foodImgRepository.findByIdAndRepImgYn(foodImgId, repImgYn)
                    .orElseThrow(EntityNotFoundException::new);

            // 1. 기존에 파일시스템에 저장되어 있던 이미지 삭제
            if (StringUtils.hasText(existingFoodImg.getImgName())){
                fileService.deleteFile(FoodImgLocation + "/" + existingFoodImg.getImgName());
            }

            // 2. DB에 기존 레코드 내용 업데이트
            String orgFileName = foodImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(FoodImgLocation, orgFileName, foodImgFile.getBytes());
            String imgUrl = "/images/item/" + imgName;

            // updateFoodImg 메서드의 파라미터 순서에 맞게 수정
            existingFoodImg.updateFoodImg(orgFileName, imgName, imgUrl);
        }
    }
}