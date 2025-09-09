package com.back.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberInfoDto {

    private String memberId;
    private String nickname;

}
