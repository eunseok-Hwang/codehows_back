package com.back.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private String username;
    private String password;
    private String userId;
    private String nickname;

}
