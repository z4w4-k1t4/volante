package jp.co.volante.microblog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MicroBlogRepository extends JpaRepository<MicroBlog, MicroBlogId> {

    // 情報取得
    List<MicroBlog> findById(Long keyword); // Idで情報取得

    List<MicroBlog> findByBranchNo(Long keyword); // 更新枝番号で情報取得

    List<MicroBlog> findByPostContentContaining(String keyword); // 投稿内容で情報取得

    List<MicroBlog> findByOwnerUserId(Long keyword); // 投稿者IDで情報取得

    List<MicroBlog> findByCreateAt(LocalDateTime keyword); // 作成日時で情報取得

    List<MicroBlog> findByCreateUserId(Long keyword); // 作成ユーザIDで情報取得

    List<MicroBlog> findByDeleteAt(LocalDateTime keyword); // 削除日時で情報取得

    List<MicroBlog> findByPostContentAndCreateAt(String postContent, LocalDateTime createAt); // 投稿内容,作成日時で情報取得

    List<MicroBlog> findTop10ByDeleteAtIsNullOrderByCreateAtDesc(); // 削除日時がNullのレコード取得(最新10件を作成日時降順)

    // 削除日時の更新
    @Modifying
    @Query("Update MicroBlog mb SET mb.deleteAt = :deleteAt WHERE mb.id = :id AND mb.branchNo = :branchNo")
    void updateDeleteAt(@Param("id") Long id, @Param("branchNo") Long branchNo,
            @Param("deleteAt") LocalDateTime deleteAt);
}
