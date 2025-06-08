package jp.co.volante.microblog;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable

public class MicroBlogId implements Serializable {

    private Long id; // 管理番号

    @Column(name = "branch_no")
    private Long branchNo; // 更新枝番

    public MicroBlogId() {
    } // デフォルトコンストラクタ(必須らしい)

    // パラメータ付きコンストラクタ
    public MicroBlogId(Long id, Long branchNo) {
        this.id = id;
        this.branchNo = branchNo;
    }

    // Getterを定義
    public Long getId() {
        return id;
    }

    public Long getBrunch_no() {
        return branchNo;
    }

    // Setterを定義
    public void setId(Long id) {
        this.id = id;
    }

    public void setBrunch_no(Long branchNo) {
        this.branchNo = branchNo;
    }
}
