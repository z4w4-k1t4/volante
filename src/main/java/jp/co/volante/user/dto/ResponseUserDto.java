package jp.co.volante.user.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResponseUserDto {
    private Long id;
    private String uid;
    private String displayName;
    private String email;
    private LocalDateTime createAt;
    private Long createUserId;
    private LocalDateTime updateAt;
    private Long updateUserId;
    private LocalDateTime deleteAt;
    private Long deleteUserId;
}
