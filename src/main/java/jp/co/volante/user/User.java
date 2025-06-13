package jp.co.volante.user;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT COMMENT '管理番号'")
    private Long id;

    @Column(name = "uid", length = 100, columnDefinition = "VARCGAR(100) COMMENT 'firebase管理番号'")
    private String uid;

    @Column(name = "display_name", length = 50, columnDefinition = "VARCHAR(50) COMMENT 'ユーザー表示名'")
    private String displayName;

    @Column(name = "email", length = 100, columnDefinition = "VARCHAR(100) COMMENT 'メールアドレス'")
    private String email;

    @CreatedDate
    @Column(name = "create_at", columnDefinition = "COMMENT '作成日時'", updatable = false)
    private LocalDateTime createAt;

    @Column(name = "create_user_id", columnDefinition = "BIGINT COMMENT '作成ユーザーID'")
    private Long createUserId;

    @LastModifiedDate
    @Column(name = "updata_at", columnDefinition = "COMMENT '更新日時'")
    private LocalDateTime updateAt;

    @Column(name = "update_user_id", columnDefinition = "BIGINT COMMENT '更新ユーザーID'")
    private Long updateUserId;

    @Column(name = "delete_at", columnDefinition = "DATETIME COMMENT '削除日時'")
    private LocalDateTime deleteAt;

    @Column(name = "delete_user_id", columnDefinition = "BIGINT COMMENT '削除ユーザーID'")
    private Long deleteUserId;
}