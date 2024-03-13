package shop.onekorea.springboot2.controller.secondary;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.onekorea.springboot2.dto.Post2ResponseDto;
import shop.onekorea.springboot2.entity.secondary.Post2;
import shop.onekorea.springboot2.service.secondary.Post2Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static shop.onekorea.springboot2.Springboot2Application.getInfo;


@RestController
@RequiredArgsConstructor
@RequestMapping("/posts2")
public class Post2RestController {

    private final Post2Service post2Service;
//    private PostService postService; 아래와 같은 치명적 에러 발생.

    /**
     * @conclusion. 위에서 "postService" 선언 시, "final"을 빼고 [private PostService postService] 이렇게 선언하면,
     * 아래와 같은 치명적 에러 발생함.
     * ***** Error *****
     *      Servlet.service() for servlet [dispatcherServlet] in context with path []
     *          threw exception [Request processing failed: java.lang.NullPointerException: Cannot invoke
     *          "shop.onekorea.springboot2.service.PostService.postList1()"
     *          because "this.postService" is null] with root cause
     *          java.lang.NullPointerException: Cannot invoke
     *          "shop.onekorea.springboot2.service.PostService.postList1()"
     *          because "this.postService" is null at
     *          shop.onekorea.springboot2.controller.PostRestController.postList(PostRestController.java:42) ~[main/:na]
     */

//    public PostRestController(PostService postService) {
//        this.postService = postService;
//    }

//    @GetMapping("") // localhost:8080/posts // [localhost:8080/posts/] 끝에 "/"를 주면, 브라우저에서도 반드시 "/"를 붙혀 주어야 된다는 것에 주의.
//    public String postString() {
//        return "SpringBoot2 메인 페이지입니다. ";
//    }

    @GetMapping("/list")
    public List<Post2> post2List() {
        System.err.println(getInfo() + " post2List: ");
        List<Post2> post2List = post2Service.post2List();
        System.err.println(getInfo() + " post2List: " + post2List.size());

        return post2List;
    }

//    @GetMapping("/list0") /* localhost:8080/posts/list */
//    // public List<String> getList() {
//    //    List<String> stringList = new ArrayList<>();
//    //    return stringList;
//    // }
//    // ===> Post.java 파일 생성 후 아래와 같이 변경할 수 있다.
//    public List<Post2> getPostList() {
//        List<Post2> postList = List.of(
////                new Post(1, "title1", "contents1", "author1", LocalDateTime.now()),
////                new Post(2, "title2", "contents2", "author2", LocalDateTime.now()),
////                new Post(3, "title3", "contents3", "author3", LocalDateTime.now())
//                new Post2(1L, UUID.randomUUID().toString(),"title1", "contents1", "author1", LocalDateTime.now()),
//                new Post2(2L, UUID.randomUUID().toString(), "title2", "contents2", "author2", LocalDateTime.now()),
//                new Post2(3L, UUID.randomUUID().toString(), "title3", "contents3", "author3", LocalDateTime.now())
//        );
//        return postList;
//        // 대괄호 + 중괄호 임에 주의 : 아래와 같이 리턴 된다.
//        // [{"postId": "1", "title": "title1", "contents": "contents1", "author": "author1", "createdAt": "2024-02-01T07:09:35.223956"},
//        // {"postId": "2", "title": "title2", "contents": "contents2", "author": "author2", "createdAt": "2024-02-01T07:09:35.223956"},
//        // {"postId": "3", "title": "title3", "contents": "contents3", "author": "author3", "createdAt": "2024-02-01T07:09:35.223956"}]
//    }


//    @GetMapping("/{postId}") /* localhost:8080/posts/3 */
//    // public String getPostId(@PathVariable String postId) { // 위 "{postId}"와 여기 "postId" 이것은 반드시 동일하게 맞추어 주어야 한다.
//    //    String rtn = postId + "블로그 상세 내용입니다.";
//    //    return rtn;
//    // }
//    // ===> Post.java 파일 생성 후 아래와 같이 변경할 수 있다.
//    public Post2 getPost2ById(@PathVariable String postId) { /* localhost:8080/posts/3 */
////        Post post = new Post(1, "title1", "contents1", "author1", LocalDateTime.now());
//        Post2 post2 = new Post2(1L, UUID.randomUUID().toString(), "title1", "contents1", "author1", LocalDateTime.now());
//        return post2; // 중괄호 임에 주의 : {"postId": "1", "title": "title1", "contents": "contents1", "author": "author1", "createdAt": "2024-02-01T07:09:35.223956"}
//    }

    @GetMapping("/{postId}")
    public Optional<Post2> findById(@PathVariable Long postId) {
        Optional<Post2> post2 = post2Service.findById(postId);
        return post2;
    }

    @GetMapping("/update") /* localhost:8080/posts/update?postId=3 */
    // public String updatePost(@RequestParam String postId) {
    //    return postId + " 블로그 수정 페이지입니다.";
    // }
    // ===> Post.java 파일 생성 후 아래와 같이 변경할 수 있다.
    public Post2 updatePost2(@RequestParam String postId) { /* localhost:8080/posts/update?postId=3 */
//        Post post = new Post(3, "title3", "contents3", "author3", LocalDateTime.now());
        Post2 post2 = new Post2(3L, UUID.randomUUID().toString(), "0000", "title3", "contents3", "author3", LocalDateTime.now(), null);
        return post2; // 중괄호 임에 주의 : {"postId": "3", "title": "title3", "contents": "contents3", "author": "author3", "createdAt": "2024-02-01T07:09:35.223956"}
    }

    @PostMapping("/id")
    public Optional<Post2> getPost2(@RequestBody Post2 post2) {
        Optional<Post2> post2ResponseDto = post2Service.findById(post2.getId());
        System.err.println(getInfo() + " post2ResponseDto: " + post2ResponseDto);
        System.err.println(getInfo() + " post2ResponseDto.getClass().getName(): " + post2ResponseDto.getClass().getName());
        return post2ResponseDto;
    }

}

