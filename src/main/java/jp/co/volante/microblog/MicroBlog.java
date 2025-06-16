
//model/MicroBlog.java

package jp.co.volante.microblog;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@IdClass(MicroBlogId.class)
@Entity
@Table(name = "micro_blog")

public class MicroBlog {

    @Id
    private Long id; // 管理番号

    @Id
    @Column(name = "branch_no")
    private Long branchNo; // 更新枝番

    @Column(name = "post_content")
    private String postContent; // 投稿内容

    @Column(name = "owner_user_id")
    private Long ownerUserId; // 所有者ユーザーID

    @Column(name = "create_at")
    private LocalDateTime createAt; // 作成日時

    @Column(name = "create_user_id")
    private Long createUserId; // 作成ユーザーID

    @Column(name = "delete_at")
    private LocalDateTime deleteAt; // 削除日時

    @Column(name = "delete_user_id")
    private Long deleteUserId; // 削除ユーザーID

    // Getterを定義
    public Long getId() {
        return id;
    } // 管理番号

    public Long getBranchNo() {
        return branchNo;
    } // 更新枝番号

    public String getPostContent() {
        return postContent;
    } // 投稿内容

    public Long getOwnerUserId() {
        return ownerUserId;
    } // 所有者ユーザID

    public LocalDateTime getCreateAt() {
        return createAt;
    } // 作成日時

    public Long getCreateUserId() {
        return createUserId;
    } // 作成者ユーザID

    public LocalDateTime getDeleteAt() {
        return deleteAt;
    } // 削除日時

    public Long getDeleteUserId() {
        return deleteUserId;
    } // 削除ユーザID

    // Setterを定義
    public void setId(Long id) {
        this.id = id;
    }// 管理番号

    public void setBranchNo(Long branchNo) {
        this.branchNo = branchNo;
    }// 更新枝番号

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    } // 投稿内容

    public void setOwnerUserId(Long ownerUserId) {
        this.ownerUserId = ownerUserId;
    } // 所有者ユーザID

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    } // 作成日時

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    } // 作成者ユーザID

    public void setDeleteAt(LocalDateTime deleteAt) {
        this.deleteAt = deleteAt;
    } // 削除日時

    public void setDeleteUserId(Long deleteUserId) {
        this.deleteUserId = deleteUserId;
    } // 削除ユーザID

}
