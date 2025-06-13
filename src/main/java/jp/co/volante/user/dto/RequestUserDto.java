package jp.co.volante.user.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RequestUserDto {
    private Long id;
    private String uid;
    private String displayName;
    private String email;
    private Long createUserId;
    private Long updateUserId;
    private LocalDateTime deleteAt;
    private Long deleteUserId;
}
