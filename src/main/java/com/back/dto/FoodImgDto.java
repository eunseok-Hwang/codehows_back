package com.back.dto;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class FoodImgDto {
    private String id;
    private String imgName;
    private String orgFileName;
    private String repImgYn;
    private String imgType;

    private static ModelMapper modelMapper = new ModelMapper();

    public static FoodImgDto getFoodImgDto(FoodImgDto foodImgDto) {
        return modelMapper.map(foodImgDto, FoodImgDto.class);
    }
}
