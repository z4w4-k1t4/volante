package jp.co.volante.microblog;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import jp.co.volante.microblog.dto.RequestMicroBlogDto;
import jp.co.volante.microblog.dto.ResponseMicroBlogDto;

@RestController
@RequestMapping("/api/")

public class MicroBlogController {

    private final MicroBlogService microBlogService;

    public MicroBlogController(MicroBlogService microBlogService) {
        this.microBlogService = microBlogService;
    }

    // アクティブな状態(削除日時がNull)の投稿を取得
    @GetMapping("/micro-blog")
    public List<MicroBlog> getActiveBlogs() {
        return microBlogService.getActiveBlogs();
    }

    // 投稿の新規登録(branchNo,postContent,ownerUserId,createAtをPOSTリクエストに送る)
    @PostMapping("/new-post")
    public ResponseEntity<Map<String, Object>> createContent(@RequestBody MicroBlog microBlog) {
        MicroBlog savedBlog = microBlogService.createMicroBlog(microBlog);

        Map<String, Object> response = new HashMap<String, Object>();
        response.put("message", "投稿に成功しました！");
        response.put("id", savedBlog.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 削除フラグの更新(id,branchNo,deleteAtをPUTリクエストに送る)
    @PutMapping("/post-delete")
    public ResponseEntity<Map<String, Object>> deleteContent(@RequestBody MicroBlog microBlog) {
        MicroBlog deleted = microBlogService.updateDeleteAt(
                microBlog.getId(),
                microBlog.getBranchNo(),
                microBlog.getDeleteAt());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "削除処理が完了しました");
        response.put("id", deleted.getId());
        response.put("branchNo", deleted.getBranchNo());
        response.put("deleteAt", deleted.getDeleteAt());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/microblogs/{id}/{branchNo}")
    public ResponseMicroBlogDto getMicroBlogById(@PathVariable Long id, @PathVariable Long branchNo) {
        return microBlogService.getMicroBlogById(id, branchNo);
    }

    @GetMapping("/microblogs/postContent/{id}/{branchNo}")
    public List<ResponseMicroBlogDto> getMicroBlogsByPostContent(@RequestParam String postContent) {
        return microBlogService.getMicroBlogsByPostContent(postContent);
    }

    @GetMapping("/microblogs/ownerUserId/{id}/{branchNo}")
    public List<ResponseMicroBlogDto> getMicroBlogsByOwnerUserId(@RequestParam Long ownerUserId) {
        return microBlogService.getMicroBlogsByOwnerUserId(ownerUserId);
    }
}
