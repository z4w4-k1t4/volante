package jp.co.volante.microblog;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MicroBlogService {

    private final MicroBlogRepository microBlogRepository;

    public MicroBlogService(MicroBlogRepository microBlogRepository) {
        this.microBlogRepository = microBlogRepository;
    }

    // 削除日時がNullのレコード取得(最新10件を作成日時降順)
    public List<MicroBlog> getActiveBlogs() {
        return microBlogRepository.findTop10ByDeleteAtIsNullOrderByCreateAtDesc();
    }

    // レコードの登録
    public MicroBlog createMicroBlog(MicroBlog microBlog) {
        return microBlogRepository.save(microBlog);
    }

    // 削除日時の更新
    @Transactional
    public MicroBlog updateDeleteAt(Long id, Long branchNo, LocalDateTime deleteAt) {
        microBlogRepository.updateDeleteAt(id, branchNo, deleteAt);

        MicroBlog deleteBlog = microBlogRepository.findById(new MicroBlogId(id, branchNo))
                .orElseThrow(() -> new RuntimeException("対象の投稿が見つかりません"));
        deleteBlog.setDeleteAt(deleteAt);
        return microBlogRepository.save(deleteBlog);
    }

}
