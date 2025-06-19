package jp.co.volante.microblog;

import java.io.Serializable;
import java.util.Objects;
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

    public Long getBranchNo() {
        return branchNo;
    }

    // Setterを定義
    public void setId(Long id) {
        this.id = id;
    }

    public void setBranchNo(Long branchNo) {
        this.branchNo = branchNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof MicroBlogId))
            return false;
        MicroBlogId that = (MicroBlogId) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(branchNo, that.branchNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, branchNo);
    }

}
