package shop.onekorea.springboot2.controller.primary;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.onekorea.springboot2.dto.Post1ResponseDto;
import shop.onekorea.springboot2.dto.Post1ResponseMapper;
import shop.onekorea.springboot2.entity.primary.Post1;
import shop.onekorea.springboot2.service.primary.Post1Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static shop.onekorea.springboot2.Springboot2Application.getInfo;


@RestController
@RequiredArgsConstructor
@RequestMapping("/posts1")
public class Post1RestController {

    private final Post1ResponseMapper post1ResponseMapper;

    private final Post1Service post1Service;
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

    @GetMapping("") // localhost:8080/posts // [localhost:8080/posts/] 끝에 "/"를 주면, 브라우저에서도 반드시 "/"를 붙혀 주어야 된다는 것에 주의.
    public String postString() {
        return "SpringBoot2 메인 페이지입니다. ";
    }

    /* [password] 컬럼까지 모든 Entity 자료가 표시 */
    @GetMapping("/list")
    public List<Post1> post1List() {
        System.err.println(getInfo());
        List<Post1> post1List = post1Service.getPost1List();
        System.err.println(getInfo() + " post1List: " + post1List);
//        post1List: [
//          Post1(id=1, postId=8b89ab8e-bace-474f-8dcc-adff6793a462, password=$2a$10$xHyF791R3JYn70HXAsIAHOs94bNFqZfGSXVHyEVJu.W.ky6NgIdxy, title=타이틀 0001, contents=컨텐츠 0001, author=rwkang@naver.com, createdAt=2024-03-09T04:26:09.257451, modifiedAt=2024-03-09T04:26:09.257451),
//          Post1(id=2, postId=885b62c6-24f1-4202-9b07-58c31a23a0ff, password=$2a$10$7t7g6WIe4cLwHIvDtgOrcePn8VT25LmLJ.mj2.hqYTErmQ1xBZRAW, title=타이틀 0002, contents=컨텐츠 0002, author=rwkang@naver.com, createdAt=2024-03-09T04:26:09.333470, modifiedAt=2024-03-09T04:26:09.333470),
//          Post1(id=3, postId=338d452e-62fc-4d0c-9afe-cf3c1ae3f82d, password=$2a$10$zdEhVT9RyDava6p1iBbqHuHqbHqP.bay0ZDJGgLQnBzS7W2xcgvT2, title=타이틀 0003, contents=컨텐츠 0003, author=rwkang@naver.com, createdAt=2024-03-09T04:26:09.410102, modifiedAt=2024-03-09T04:26:09.410102),
//          Post1(id=4, postId=9c0a3dfb-a912-4698-a01f-f6d5ffee0d56, password=$2a$10$EXhfEjXPYiA/y6meQRIlAOx/O3mci09QXfg2ehAUVOLK.iJP4BvHW, title=타이틀 0004, contents=컨텐츠 0004, author=rwkang@naver.com, createdAt=2024-03-09T04:26:09.483468, modifiedAt=2024-03-09T04:26:09.483468),
//          Post1(id=5, postId=4c49db36-b0ea-4f16-91e5-8dbea3bb9672, password=$2a$10$EF0VGwgaCUGnQHtUQrTRU.aRdwwGCBe0fDlY76XvxnANUVFiS9Aw., title=타이틀 0005, contents=컨텐츠 0005, author=rwkang@naver.com, createdAt=2024-03-09T04:26:09.556351, modifiedAt=2024-03-09T04:26:09.556351),
//          Post1(id=6, postId=cd18742a-f1ae-43bc-a3c5-42801eafb52c, password=$2a$10$lmv10W9.Mupiupir5rtcMuym5fy6gq3msDnh1PcqUbNGTOazpwrrS, title=타이틀 0006, contents=컨텐츠 0006, author=rwkang@naver.com, createdAt=2024-03-09T04:26:09.630177, modifiedAt=2024-03-09T04:26:09.630177),
//          Post1(id=7, postId=06b62316-2d19-4688-8aa2-f80e0a965389, password=$2a$10$c0z0XngfSkwpCnoTknKFp.DWnXWNNYMFsxFtCMvmyZejBD5HkUm9S, title=타이틀 0007, contents=컨텐츠 0007, author=rwkang@naver.com, createdAt=2024-03-09T04:26:09.704678, modifiedAt=2024-03-09T04:26:09.705191)
//        ]
        System.err.println(getInfo() + " post1List: " + post1List.size());// 레코드 수
        System.err.println(getInfo() + " post1List.getClass().getName(): " + post1List.getClass().getName()); // java.util.ArrayList

        return post1List;
    }

    /* [password] 컬럼을 뺀 자료. 그러나 modifiedAt 컬럼 값이 실제 테이블에 저장된 값이 표시. (만약 "null" 이면, 그대로 "null"로 표시됨) */
    @GetMapping("/response")
    public List<Post1ResponseDto> getResponsePost1List() {
        List<Post1ResponseDto> post1ResponseList = post1Service.getPost1List().stream()
                .map(p -> Post1ResponseDto.builder()
                        .id(p.getId())
                        .postId(p.getPostId())
                        .title(p.getTitle())
                        //.password(p.getPassword())
                        .author(p.getAuthor())
                        .contents(p.getContents())
                        .createdAt(p.getCreatedAt())
                        .build()).toList();
                        //.map(Post::getInfo).collect(Collectors.toList()); // java 17 이전 버전.
        System.err.println(getInfo() + " post1ResponseList: " + post1ResponseList);
//        post1ResponseList: [
//          Post1Response(id=1, postId=8b89ab8e-bace-474f-8dcc-adff6793a462, password=null, title=타이틀 0001, contents=컨텐츠 0001, author=rwkang@naver.com, createdAt=2024-03-09T04:26:09.257451, modifiedAt=null),
//          Post1Response(id=2, postId=885b62c6-24f1-4202-9b07-58c31a23a0ff, password=null, title=타이틀 0002, contents=컨텐츠 0002, author=rwkang@naver.com, createdAt=2024-03-09T04:26:09.333470, modifiedAt=null),
//          Post1Response(id=3, postId=338d452e-62fc-4d0c-9afe-cf3c1ae3f82d, password=null, title=타이틀 0003, contents=컨텐츠 0003, author=rwkang@naver.com, createdAt=2024-03-09T04:26:09.410102, modifiedAt=null),
//          Post1Response(id=4, postId=9c0a3dfb-a912-4698-a01f-f6d5ffee0d56, password=null, title=타이틀 0004, contents=컨텐츠 0004, author=rwkang@naver.com, createdAt=2024-03-09T04:26:09.483468, modifiedAt=null),
//          Post1Response(id=5, postId=4c49db36-b0ea-4f16-91e5-8dbea3bb9672, password=null, title=타이틀 0005, contents=컨텐츠 0005, author=rwkang@naver.com, createdAt=2024-03-09T04:26:09.556351, modifiedAt=null),
//          Post1Response(id=6, postId=cd18742a-f1ae-43bc-a3c5-42801eafb52c, password=null, title=타이틀 0006, contents=컨텐츠 0006, author=rwkang@naver.com, createdAt=2024-03-09T04:26:09.630177, modifiedAt=null),
//          Post1Response(id=7, postId=06b62316-2d19-4688-8aa2-f80e0a965389, password=null, title=타이틀 0007, contents=컨텐츠 0007, author=rwkang@naver.com, createdAt=2024-03-09T04:26:09.704678, modifiedAt=null)
//        ]
        System.err.println(getInfo() + " post1ResponseList.getClass().getName(): " + post1ResponseList.getClass().getName()); // java.util.ImmutableCollections$ListN

        return post1ResponseList;
    }

    /* ===> 위 지저분한 "controller 코드"를, 아래와 같이 깔끔하게 정리하고, 다만, "PostResponseMapper.java" 파일을 새로 만든다.  */
    /* [password] 컬럼을 뺀 자료. 그러나 modifiedAt 컬럼 값이, 사용자가 원하는 값으로 표시. (만약 "null" 이면, "createdAt" 값이 표시됨) */
    @GetMapping("/mapper")
    public List<Post1ResponseDto> getMapperPost1List() {
        List<Post1ResponseDto> post1MapperList = post1Service.getPost1List().stream().map(post1ResponseMapper).toList();
        System.err.println(getInfo() + " post1MapperList: " + post1MapperList);
//        post1MapperList: [
//          Post1Response(id=1, postId=1157adf5-265f-4c0b-af15-af9141ddcc14, password=null, title=타이틀 0001, contents=컨텐츠 0001, author=rwkang@naver.com, createdAt=2024-03-09T04:19:44.320320, modifiedAt=2024-03-09T04:19:44.320320),
//          Post1Response(id=2, postId=debc01cf-5472-4197-8d6b-7d1d9e580022, password=null, title=타이틀 0002, contents=컨텐츠 0002, author=rwkang@naver.com, createdAt=2024-03-09T04:19:44.395063, modifiedAt=2024-03-09T04:19:44.395063),
//          Post1Response(id=3, postId=75310126-3065-4a93-843d-9f3dc0dc87e5, password=null, title=타이틀 0003, contents=컨텐츠 0003, author=rwkang@naver.com, createdAt=2024-03-09T04:19:44.469773, modifiedAt=2024-03-09T04:19:44.469773),
//          Post1Response(id=4, postId=dd04b071-b152-4567-b8d5-a17fa5c669ca, password=null, title=타이틀 0004, contents=컨텐츠 0004, author=rwkang@naver.com, createdAt=2024-03-09T04:19:44.549837, modifiedAt=2024-03-09T04:19:44.549837),
//          Post1Response(id=5, postId=af35d33a-0224-4275-80c0-bc838886a1fb, password=null, title=타이틀 0005, contents=컨텐츠 0005, author=rwkang@naver.com, createdAt=2024-03-09T04:19:44.623113, modifiedAt=2024-03-09T04:19:44.623113),
//          Post1Response(id=6, postId=bc8cc20c-5c15-4045-a18f-5122cffc0ad5, password=null, title=타이틀 0006, contents=컨텐츠 0006, author=rwkang@naver.com, createdAt=2024-03-09T04:19:44.695838, modifiedAt=2024-03-09T04:19:44.695838)
//        ]
        System.err.println(getInfo() + " post1MapperList.getClass().getName(): " + post1MapperList.getClass().getName()); // java.util.ImmutableCollections$ListN

        return post1MapperList;
    }

    @GetMapping("/list0") /* localhost:8080/posts/list */
    // public List<String> getList() {
    //    List<String> stringList = new ArrayList<>();
    //    return stringList;
    // }
    // ===> Post.java 파일 생성 후 아래와 같이 변경할 수 있다.
    public List<Post1> getPost1List() {
        List<Post1> postList = List.of(
//                new Post(1, "title1", "contents1", "author1", LocalDateTime.now()),
//                new Post(2, "title2", "contents2", "author2", LocalDateTime.now()),
//                new Post(3, "title3", "contents3", "author3", LocalDateTime.now())
                new Post1(1L, UUID.randomUUID().toString(),"0000", "title1", "contents1", "author1", LocalDateTime.now(), null),
                new Post1(2L, UUID.randomUUID().toString(), "0000", "title2", "contents2", "author2", LocalDateTime.now(), null),
                new Post1(3L, UUID.randomUUID().toString(), "0000", "title3", "contents3", "author3", LocalDateTime.now(), null)
                );
        return postList;
        // 대괄호 + 중괄호 임에 주의 : 아래와 같이 리턴 된다.
        // [{"postId": "1", "title": "title1", "contents": "contents1", "author": "author1", "createdAt": "2024-02-01T07:09:35.223956"},
        // {"postId": "2", "title": "title2", "contents": "contents2", "author": "author2", "createdAt": "2024-02-01T07:09:35.223956"},
        // {"postId": "3", "title": "title3", "contents": "contents3", "author": "author3", "createdAt": "2024-02-01T07:09:35.223956"}]
    }


//    @GetMapping("/{postId}") /* localhost:8080/posts/3 */
//    // public String getPostId(@PathVariable String postId) { // 위 "{postId}"와 여기 "postId" 이것은 반드시 동일하게 맞추어 주어야 한다.
//    //    String rtn = postId + "블로그 상세 내용입니다.";
//    //    return rtn;
//    // }
//    // ===> Post.java 파일 생성 후 아래와 같이 변경할 수 있다.
//    public Post1 getPost1ById(@PathVariable String postId) { /* localhost:8080/posts1/3 */
////        Post1 post1 = new Post1(1L, "title1", "contents1", "author1", LocalDateTime.now());
//        Post1 post1 = new Post1(1L, UUID.randomUUID().toString(), "title1", "contents1", "author1", LocalDateTime.now());
//        return post1; // 중괄호 임에 주의 : {"postId": "1", "title": "title1", "contents": "contents1", "author": "author1", "createdAt": "2024-02-01T07:09:35.223956"}
//    }

    @GetMapping("/id/{postId}")
    public Optional<Post1> getPost1ById(@PathVariable Long postId) { /* localhost:8080/posts1/id/3 */
        Optional<Post1> post1Optional = post1Service.getPost1ById(postId);
        return post1Optional;
    }

    @GetMapping("/title/{title}")
    public Optional<Post1> getPost1ByTitle(@PathVariable String title) { /* localhost:8080/posts1/title/타이틀 0001 */
        Optional<Post1> post1Optional = post1Service.getPost1ByTitle(title);
        return post1Optional;
    }

//    @GetMapping("/title") // 아래 2개는 동일하네. [title?title=타이틀 0001]"
//    public Post1 getFindByTitle(@RequestParam("title") String title) { /* http://localhost:8088/posts1/title?title=타이틀 0001 */
////    public Post1 getFindByTitle(@RequestParam("") String title) { /* http://localhost:8088/posts1/title?title=타이틀 0001 */
//        Post1 post1 = post1Service.getPost1ByTitle(title);
//        return post1;
//    }

    @GetMapping("/update") /* localhost:8080/posts/update?postId=3 */
    // public String updatePost(@RequestParam String postId) {
    //    return postId + " 블로그 수정 페이지입니다.";
    // }
    // ===> Post.java 파일 생성 후 아래와 같이 변경할 수 있다.
    public Post1 updatePost1(@RequestParam String postId) { /* localhost:8080/posts/update?postId=3 */
//        Post post = new Post(3, "title3", "contents3", "author3", LocalDateTime.now());
        Post1 post = new Post1(3L, UUID.randomUUID().toString(), "0000", "title3", "contents3", "author3", LocalDateTime.now(), null);
        return post; // 중괄호 임에 주의 : {"postId": "3", "title": "title3", "contents": "contents3", "author": "author3", "createdAt": "2024-02-01T07:09:35.223956"}
    }

    @PostMapping("/id")
    public Optional<Post1> getPost1(@RequestBody Post1 post1) {
        Optional<Post1> post1Optional = post1Service.getPost1ById(post1.getId());
        System.err.println(getInfo() + " post1Optional: " + post1Optional);
        System.err.println(getInfo() + " post1Optional.getClass().getName(): " + post1Optional.getClass().getName()); // java.util.Optional
        return post1Optional;
    }

    /* 2024.03.09 Conclusion. Post1ResponseDto 객체로 바로 받는 방법이 없네. 위에서 처럼,
    *  1. @GetMapping("/mapper") 이 방법이나,
    *  2. @GetMapping("/response") 이 방법으로 처리해야 된다는 결론. */
//    @PostMapping("/dtoid")
//    public Post1ResponseDto getPost1Dto(@RequestBody Post1 post1) {
//        Post1ResponseDto post1ResponseDto = post1Service.getPost1DtoById(post1.getId());
//        System.err.println(getInfo() + " post1ResponseDto: " + post1ResponseDto); // Optional[Post1(id=1, postId=5189c999-1d4a-4ac8-8234-dc046de92124, password=$2a$10$YLkME2LwjKiUbt3fixr6OOlu5oweuIdxrP1IGbyBwI9G8Rv34lB0u, title=타이틀 0001, contents=컨텐츠 0001, author=rwkang@naver.com, createdAt=2024-03-09T04:14:34.064844, modifiedAt=2024-03-09T04:14:34.064844)]
//        System.err.println(getInfo() + " post1ResponseDto.getClass().getName(): " + post1ResponseDto.getClass().getName()); // java.util.Optional
//        return post1ResponseDto;
    }

}
