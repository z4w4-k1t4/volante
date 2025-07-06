package jp.co.volante.microblog;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import jp.co.volante.microblog.dto.ResponseMicroBlogDto;

@Service
public class MicroBlogService {

    private final MicroBlogRepository microBlogRepository;

    private ResponseMicroBlogDto toResponseDto(MicroBlog microBlog) {
        ResponseMicroBlogDto dto = new ResponseMicroBlogDto();
        dto.setId(microBlog.getId());
        dto.setBranchNo(microBlog.getBranchNo());
        dto.setPostContent(microBlog.getPostContent());
        dto.setOwnerUserId(microBlog.getOwnerUserId());
        dto.setCreateAt(microBlog.getCreateAt());
        dto.setDeleteAt(microBlog.getDeleteAt());

        return dto;
    }

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

    public ResponseMicroBlogDto getMicroBlogById(Long id, Long branchNo) {
        MicroBlogId microBlogId = new MicroBlogId(id, branchNo);
        return microBlogRepository
                .findById(microBlogId)
                .map(this::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("microBlogId not found"));
    }

    public List<ResponseMicroBlogDto> getMicroBlogsByPostContent(String postContent) {
        return microBlogRepository.findByPostContent(postContent).stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<ResponseMicroBlogDto> getMicroBlogsByOwnerUserId(Long ownerUserId) {
        return microBlogRepository.findByOwnerUserId(ownerUserId).stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

}
