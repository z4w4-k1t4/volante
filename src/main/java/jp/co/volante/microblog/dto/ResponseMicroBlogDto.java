package jp.co.volante.microblog.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResponseMicroBlogDto {
    private Long id; // 管理番号
    private Long branchNo; // 更新枝番
    private String postContent; // 投稿内容
    private Long ownerUserId; // 所有者ユーザーID
    private LocalDateTime createAt; // 作成日時
    private Long createUserId; // 作成ユーザーID
    private LocalDateTime deleteAt; // 削除日時
    private Long deleteUserId; // 削除ユーザーID

}
