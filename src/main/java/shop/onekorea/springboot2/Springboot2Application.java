/**
 * Spring Boot.스프링 부트 이해
 * // 2024.01.31
 * *. G:\java\workspace\springboot 프로젝트 한번 더 최종 점검.
 * 코딩하는오후: 스프링부트 : 프로젝트 생성과 구조 이해
 * https://www.youtube.com/watch?v=ZoM0l19Dfbk&list=PLeMeDIV7bypvyxWv7eIUZubmfx9W-Jjdg
 *
 *
 * // 2023.10.01
 * *. Spring.Security.JWT(Java Web Token) 이해.
 *
 *      1. application.yml 파일을 통한 JPA 데이터베이스 연동 : local(H2), dev(MySQL), prod(MySQL, Oracle 실패)
 *          post.xml 파일을 통한 mybatic 데이터베이스 연동 포함
 *          https://www.youtube.com/watch?v=xpXAj1udnkU&list=PLeMeDIV7bypvyxWv7eIUZubmfx9W-Jjdg&index=3
 *
 *          => 2023.09.26 10:27 Flutter 접속 성공, PowerAppMenu Flutter Project에서 SpringBoot Project로의 "로그인 접속 성공".
 *
 *      2. jasypt.Java Simplified Encryption 보안
 *          https://www.youtube.com/watch?v=3CY2pk-Ug10&list=PLeMeDIV7bypvyxWv7eIUZubmfx9W-Jjdg&index=4
 *          => MSA 패턴에서는, Config 서버를 별도로 두고, 일반 개발자는 어떤 곳에 접속을 못하게 하는 보안을 구성할 수 있다.
 *
 * 2023.11.24
 * *.   Controller vs RestController: [Controller]는, [@Controller] vs [@RestController] 어노테이션의 사용 여부에 따라 갈라진다.
 *      [@RestController]을 사용하면, return 값으로 [.html] 파일을 사용할 수 없고,
 *      반드시, [RestAPI] 형식으로 [return] 된다.
 *
 * *.   [@RestController]는, http://localhost:8088 형식으로 사용할 수 없고,
 *      반드시, [postma] or [http client] 환경에서 사용해야 하고,
 *      /project/http-client.env.json 파일에서 환경 설정을 할 수 있고,
 *      /util/security.http 파일을 열어서 실행할 수 있다.
 *      1. [POST localhost:8088/logonJwt or POST {{url}}/auth/loginJwt] 실행한 후,
 *      2. 출력되는 [token] 값을 복사하여, [application.yml.token] 값으로 넣어 준다.
 *      3. 또 다른 [도메인]을 실행할 수 있다. 단, [@RestController] 만 그 값을 확인할 수 있다.
 *
 * 2023.12.01
 * *.   8강: QueryDSL
 *      https://www.youtube.com/watch?v=pLfUiXbVOh8&list=PLeMeDIV7bypvyxWv7eIUZubmfx9W-Jjdg&index=8
 *
 *      1. Dependency 추가
 *      2. Entity 추가: Student, Score
 *      3. Dto 추가: SubjectScoreResponseDto
 *      4. RestController 추가: SubjectScoreRestController
 *      5. Service 추가: SubjectScoreService
 *      6. Repository 추가:
 *          SubjectScoreRepository <= SubjectScoreCustom <= SubjectScoreCustomImpl <= QueryDslConfig.JPAQueryFactory
 *      7. Config 추가: QueryDslConfig.JPAQueryFactory
 *      8. security.http 실행: GET
 *          {{review-api}}/subject/score/1
 *          Content-Type: application/json
 *          Authorization: Bearer {{token}}
 *      9. Q Class 파일 확인: Project/src/main/generated/shop/onekorea/springboot/entity/QClass~~
 *          아래 블로그 참조.
 *          https://jong-bae.tistory.com/19
 *
 * 2023.12.04
 * *.   9강: 데이터베이스 초기화: ddl-auto VS schema.sql/data.sql : 스프링부트에서는 2가지 병행 사용 비 추천 함.
 *      1. application.yml 설정 컨셉
 *          - JPA로 database를 컨트롤하지 않는다.
 *              . 개발자 노트북에 설치된 db인 경우 또는 초기 개발시에만 허용하고, 이때 JPA에 의해 생성된 테이블의 DDL 스크립트 활용.
 *          - 반드시 나 혼자 개발할 때만 : 초기 필요한 데이터만 data.sql에 넣어둔다. (관리자 계정, 마스터 코드 등)
 *          - DDL 스크립트는 별도 관리한다. (DBA가 관리한다)
 *      2. application 설정
 *          - YAML
 *              // JPA Setting
 *              spring.jpa.hibernate.ddl-auto=[create|none]
 *              // SQL Init : In Memory DB가 아닌 경우, 아래 2개 라인 추가 설정해 주어야 한다.
 *              spring.sql.init.mode=[always|never]
 *              spring.sql.init.platform=[데이터베이스명=mariadb]
 *          - spring.sql.init.platform 옵션은 data-[데이터베이스명=mariadb].sql과 같이 사용할 수 있다.
 *              resources/static/data-mariadb.sql :  mariadb인 경우.
 *          - ddl-auto가 가장 먼저 동작하여야 하므로, 아래 옵션을 추가해 준다. (create 일때)
 *              spring.jpa.defer-datasource-initialization=true
 *      3. 설정 결과
 *          spring:
 *              datasources:
 *                  url: jdbc:mariadb: jdbc:mysql://localhost:3306/powerapp?serverTimezone=UTC&characterEncoding=UTF-8
 *                  driver-class-name: com.mysql.cj.jdbc.Driver
 *                  username: ENC(muc9NgC7JAvoW2VMyGJmVQ==)
 *                  password: ENC(dJCRCql0gN/hPdLE+CQWtHhRumVQa+3w)
 *               jpa:
 *                  database-platform: org.hibernate.dialect.MySQLDialect
 *                  properties:
 *                      hibernate:
 *                          show-sql: true
 *                          format-sql: true
 *                  hibernate:
 *                      ddl-auto: create => none
 *                  defer-datasource-initialization: true
 *              sql:
 *                  init:
 *                      mode: always => never
 *                      platform: mysql
 *      4. data-powerapp.sql 참고 예제.
 *          INSERT INTO `student` VALUES (1, 'clarus', 24), (2, 'hkyun', 21), (3, 'rwkang', 28);
 *          INSERT INTO `score` VALUES (1, 'korean', 95, 1), (2, 'math', 99, 1), (3, 'english', 93, 1), (4, 'english', 89, 2);
 *      5. schema.sql 참고 예제: schema.sql 파일 참조.
 *
 * 2023.12.04
 * *.   10강: @ExceptionHandler:
 *      https://www.youtube.com/watch?v=Y9rKRYFs59g&list=PLeMeDIV7bypvyxWv7eIUZubmfx9W-Jjdg&index=10
 *      #. AOP : Proxy Pattern 영상 참조.
 *          - Aspect Oriented Programming
 *          - 실행하는 함수-메소드 앞뒤로 뭔가를 하려는 시도
 *      #. @Aspect VS @PointCut
 *          - Aspect
 *          - PointCut
 *          - Before
 *          - After
 *          - AfterReturning
 *          - Around
 *          - AfterThrowing
 *      #. @ControllerAdvice
 *          - Controller에서 return 되는 값을 표준 형태로 한번 더 감쌀 수 있는데, 이것이 상기 @Aspect, @PointCut으로 동작한다.
 *          - RestControllerAdvice: ResponseBody를 만들어 준다.
 *              *. RestController는 RequestBody를 만들어 준다.
 *          1. Controller 전 후 실행을 위한 세팅 파일 추가: /config/handler/LoggingAspect
 *          2. 표준 응답 DTO 파일 추가: /config/handler/DefaultResponseBody
 *          3. 모든 Return 결과 값에 대해, 표준 응답 DTO 파일에 담기: /config/handler/DefaultResponseHandler
 *      #. @ExceptionHandler
 *          - Controller에서 발생하는 Exception을 표준 형태로 처리하거나 로깅 처리를 할 수 있다.
 *          1. 모든 Exception에 대해, 표준 응답 DTO 파일에 담기: /config/handler/DefaultExceptionHandler
 *
 * 2023.12.05
 * *.   11강: Multi Datasource 구성
 *      https://www.youtube.com/watch?v=axWrDDsOEeU&list=PLeMeDIV7bypvyxWv7eIUZubmfx9W-Jjdg&index=11
 *      #. 망 분리란?
 *          - 외부 인터넷 망을 통한 불법적인 접근과 내부 정보 유출을 차단하기 위해, [업무망]과 인터넷 연결이 가능한 [외부망]을 분리하는 조치.
 *          - SI 개발을 하다보면, 원격으로 VDI(가상 데스크탑 컴퓨터)에 접속하여 망 분리된 환경에서 개발하는 곳이 많다.
 *          - 내 컴퓨터에서는 인터넷 접속이 되지만 VDI로 원격 접속한 환경에서는 이터넷 접속이 되지 않는다.
 *              VDI에서 내 컴퓨터로 또는 반대의 경로로 파일을 주고 받을 수도 없다.
 *          - 개인 정보를 취급하는 데이터베이스라면 당연히 분리된 망에서만 접근할 수 있어야 될 것이다.
 *      2. 데이터 이관이란?
 *          - 차세대 프로젝트라 함은, 기존(Legacy=AS-IS) 시스템의 소스 코드를 완전히 뒤엎는 프로젝트가 대다수이다.
 *          - 기존(Legacy=AS-IS) 시스템의 데이터는 유지되어야 하므로, 기존(Legacy=AS-IS) 데이터를 신규로 이관해야 한다.
 *          - 대부분 DBA가 데이터 이관 작업을 진행하지만, TO-BE 시스템에 맞춰 데이터를 옮겨야 하므로 일부 데이터를 수정하여 이관하는 것이 불가피함.
 *          - 이 부분이 개발자의 책임이 되게 되는데, spring batch를 이용하거나 별도 이관 프로그램을 제작하기도 한다.
 *      3. jdbc VS connection pool
 *          - JDBC: 자바에서 데이터베이스에 접속할 수 있도록 하는 Java API. (url, username, password 필요)
 *          - JDBC만으로 데이터베이스에 접속하면, 데이터를 불러올 때 마다, 접속(connect)를 해야 하므로 비효율적이고 느리다.
 *          - 이를 보완하기 위한 것이, [connection pool] 이며, 초기에는 dbcp라는 것을 많이 사용했으나, spring에서는 [hikari]를 사용.
 *          - hikari 관련 참고 문헌
 *              . HikariCP Dead lock에서 벗어나기
 *              https://techblog.woowahan.com/2664/
 *              https://techblog.woowahan.com/2663/
 *      4. 실습
 *          - DB 분리
 *          - ChainedTransactionManager
 *      5. 실전 세팅 예제: application-local/dev/prod.yml
 *          server:
 *            port: 8088
 *          spring:
 *            datasource:
 *              hikari:
 *                 // 참고로 여기 [primary or secondary] 키워드는 spring boot에서 제공하는 것이 아니고, 커스텀 키워드 이므로,
 *                 // 이 키워드의 분리 작업을 config 폴더의 [Configuration] 파일(Primary/SecondaryDatasourceConfig.java)에서,
 *                 // 반드시 선행 처리해 주어야 한다.
 *                primary:
 *                  driver-class-name: com.mysql.cj.jdbc.Driver
 *                  url: jdbc:mysql://localhost:3306/powerapp?serverTimezone=UTC&characterEncoding=UTF-8
 *                  username: ENC(muc9NgC7JAvoW2VMyGJmVQ==)
 *                  password: ENC(dJCRCql0gN/hPdLE+CQWtHhRumVQa+3w)
 *                // 또는 아래와 같이 jdbc 이용으로 mariadb 연결.
 *                primary:
 *                  driver-class-name: org.mariadb.jdbc.Driver
 *                  jdbc-url: jdbc:mariadb://192.168.0.111:3306/powerapp
 *                  username: ENC(muc9NgC7JAvoW2VMyGJmVQ==)
 *                  password: ENC(dJCRCql0gN/hPdLE+CQWtHhRumVQa+3w)
 *                secondary:
 *                  driver-class-name: oracle.jdbc.OracleDriver
 *                  url: jdbc:oracle:thin:@localhost:1521/xe
 *                  username: scott
 *                  password: tiger
 *                // 또는 아래와 같이 postgresql을 사용할 수도 있다.
 *                secondary:
 *                  driver-class-name: org.postgresql.Driver
 *                  jdbc-url: jdbc:postgresql://192.168.0.111/postgres
 *                  username: postgres
 *                  password: ENC(dJCRCql0gN/hPdLE+CQWtHhRumVQa+3w)
 *            jpa:
 *              show-sql: true
 *              properties:
 *                hibernate:
 *                  format_sql: true
 *                  use_sql_comments: true
 *                  query:
 *
 * 2023.12.12 Conclusion. Multiple Database Connection 정리.
 * *.   11강 현재까지 결론과 향후 계획
 *      1.  현재 [MySQL] and [Oracle] 연결은 잘 안 된다는 결론이다.
 *          현재 겨우 2개 모두 연결까지 완료했으나, security.http 실행에서, [3-1]과 [3-2]만 연결 된다.
 *          =>  아마도 [JwtAuthenticationFilter]와 이하 [TokenRepositoryCustomImpl]... 등의 문제와.
 *              근본적으로 [Oracle] DB 핸들링의 미숙으로 판단된다.
 *          ∴) 향후 [다른 방법의 Multiple Database Connection]을 시도해야 할 것이다.
 *      2.  우선 [H2] Local Database와 [MySQL]을 연동해서 1차 테스트를 진행하고,
 *      3.  2차로 11강에서와 같이 [PostgreSQL]과 [MySQL] 연동을 테스트 하기로 한다.
 *
 * *. 상기 2항과 3항을 진행하기 전에, [MySQL]에서 [PowERPPSC] 데이터베이스를, [좌측 트리 메뉴]까지 완료하고, 사이트 오픈한다.
 *
 * @author : rwkang on 2023.10.01"
 * @see :
 * 4강 Security
 *      : https://www.youtube.com/watch?v=3CY2pk-Ug10&list=PLeMeDIV7bypvyxWv7eIUZubmfx9W-Jjdg&index=5&t=981s
 * 5강 Deploy
 *      : https://www.youtube.com/watch?v=2woFjJ8_5MI&list=PLeMeDIV7bypvyxWv7eIUZubmfx9W-Jjdg&index=5
 *      : https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.profiles
 *      : https://spring.io/guides/topicals/spring-boot-docker/
 */


package shop.onekorea.springboot2;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import shop.onekorea.springboot2.entity.primary.*;
import shop.onekorea.springboot2.entity.secondary.*;
import shop.onekorea.springboot2.repository.primary.*;
import shop.onekorea.springboot2.repository.secondary.*;
import shop.onekorea.springboot2.service.initdata.*;
import shop.onekorea.springboot2.service.transfer.*;
import shop.onekorea.springboot2.util.DeptCode;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static shop.onekorea.springboot2.util.DeptCode.getDeptCode;

/**
 * @conclusion 2024.02.22 by rwkang, CommandLineRunner: 개발 시, 데이터를 임시로 생성. run() method 구현 필수.
 * application.yml.spring.jpa.hibernate.ddl-auto:create(or create-drop) 세팅. 운용시, "none" or "valid" 필히 전환.
 */
@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class Springboot2Application implements CommandLineRunner {
//public class Springboot2Application {

    //    private final ApplicationConfig applicationConfig;
    private final PasswordEncoder passwordEncoder; /* 이렇게 [.java] 파일이 아닌, [class] 단위로도 [주입]을 할 수가 있네...*/

    /** 2024.03.05 Conclusion. util/getDeptCode(max, current i) */
    private final DeptCode deptCode; /* 이걸 가져오면 안 되고, 위에서 import를 메소드 getDeptCode()로 처리 해야 한다.*/

    private final Post1Repository post1Repository;
    private final Dept1Repository dept1Repository;
    private final User1Repository user1Repository;
    private final Users1Repository users1Repository;
    private final Member1Repository member1Repository;
    private final SubjectScore1Repository subjectScore1Repository;
    private final Student1Repository student1Repository;

    private final Post2Repository post2Repository;
    private final Dept2Repository dept2Repository;
//    private final User2Repository user2Repository;
//    private final Users2Repository users2Repository;
//    private final Member2Repository member2Repository;
//    private final SubjectScore2Repository subjectScore2Repository;
//    private final Student2Repository student2Repository;

    private final Dept1InitDataService dept1InitDataAddService;
    private final Post1InitDataService post1InitDataService;
    private final Users1InitDataService users1InitDataService;
    private final Score1InitDataService score1InitDataService;
    private final Student1InitDataService student1InitDataService;
    private final Menu1InitDataService menu1InitDataService;

    private final DeptDataCopyService dataCopyDeptService;
    private final PostDataCopyService dataCopyPostService;
    private final UserDataCopyService dataCopyUserService;
    private final UsersDataCopyService dataCopyUsersService;
    private final MemberDataCopyService dataCopyMemberService;
    private final MenuDataCopyService dataCopyMenuService;


    /**
     * @define Current Runtime Info, getInfo()
     * @return
     * @param
     * @author rwkang in 2024.02.22
     */
    public static String getInfo() {
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
//        System.err.println("className : " + className);
        int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        /**
         * className = "shop.onekorea.springboot.SpringbootApplication"
         * 맨 마지막 클래스 이름만 받기.
         * 이렇게 하면, 아상한 배열이 리턴된다. ∵) 정규식에서 마침표(.)는 임의의 한 문자를 의미 : className.split(".")
         * ∴) 반드시 className.split("\\.") 이렇게 처리해야 한다.
         * @author rwkang
         */
        String lastClassName = className.split("\\.")[className.split("\\.").length -1];
        String info = lastClassName + "." + lineNumber;
        return info;
    }

//    /** [application.yml] 파일에서 세팅한 값 가져오기 실험... */
//    @Value("${spring.sql.init.mode}")
//    private String sqlInitMode;

    /** [application.yml] 파일에서 세팅한 값 가져오기 실험... */
    @Value("${spring.jpa.hibernate.ddl-auto}")
//    public static String jpaHibernateDdlAuto;
    private String jpaHibernateDdlAuto;

    public static void main(String[] args) {

        SpringApplication.run(Springboot2Application.class, args);

        System.err.println(getInfo() + " main()");

    }

    // implements CommandLineRunner() 대칭 필수 구현 method
    @Override
    public void run(String ... args) throws Exception {
        System.err.println(getInfo() + " run()");

        if (jpaHibernateDdlAuto.contains("none")) {
            System.err.println(getInfo() + " jpaHibernatedDdlAuto: " + jpaHibernateDdlAuto);
            return;
        }

        if (jpaHibernateDdlAuto.contains("create")) {
            System.err.println(getInfo() + " jpaHibernatedDdlAuto: " + jpaHibernateDdlAuto);

            Random random = new Random();
            // int max = rqndom.nextInt(10);
            int max = random.nextInt(10 - 6) + 6; // from 6 to 10
            System.err.println(getInfo() + " max: " + max);
            if (max < 3) max = 3;

//            max = 9;

            List<Dept1> dept1List = dept1InitDataAddService.initDataAddService(max);
            System.err.println(getInfo() + " dept1List: " + dept1List);

            List<Post1> post1List = post1InitDataService.initDataAddService(max);
            System.err.println(getInfo() + " post1List: " + post1List);

            List<Users1> users1List = users1InitDataService.initDataAddService(max);
            System.err.println(getInfo() + " users1List: " + users1List);

            List<Score1> score1List = score1InitDataService.initDataAddService(max);
            System.err.println(getInfo() + " score1List: " + score1List);

            List<Student1> student1List = student1InitDataService.initDataAddService(max);
            System.err.println(getInfo() + " student1List: " + student1List);

            List<Menu1> menu1List = menu1InitDataService.initDataAddService(max);
            System.err.println(getInfo() + " menu1List: " + menu1List);

            ////////////////////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////////////////////
//
//
//            /** 2024.03.05 Conclusion. 초기 데이터를 /service/initdata/~~AddService()에서 처리하게 한다. */
//
//            // 초기 데이터 넣기 ///////////////////////////////////////////////////////////////////////////////////////
//
//            List<Dept1> dept1List = new ArrayList<>();
//
//            String[] dept1NameList = {"관리부", "영업부", "생산부", "자재부", "품질부", "생산 관리부", "기술부", "기획실", "업무부"};
//            String dept1CodeSuffix = "";
//            for (int i = 1; i <= max; i++) {
//                System.err.println(getInfo() + " i: " + i);
//                if (i == 0) {
//                    dept1CodeSuffix = "0001";
//                } else {
////                    dept1CodeSuffix = "1" + i * 1000;
//                    dept1CodeSuffix = String.valueOf(i * 1000 + 1);
//                }
//                Dept1 dept1 = new Dept1(
//                        (long) i,
//                        "1001" + dept1CodeSuffix,
//                        dept1NameList[i - 1],
//                        LocalDateTime.now(),
//                        LocalDateTime.now());
//                dept1List.add(dept1);
//
//                // 부서 테이터는 10개만...
//                if (i > 9) break;
//            }
//
//            System.err.println(getInfo() + " dept1List: " + dept1List);
//            dept1Repository.saveAll(dept1List);
//
//
//            ////////////////////////////////////////////////////////////////////////////////////////////////
//
//            List<Post1> post1List = new ArrayList<>();
//
//            /**
//             * UUID V4: UUID.randomUUID().toString()
//             * "UUID" 타입으로 직접 지정하면, 글자가 깨지는 현상이 발생하면서, MySQL DB에서는 제대로 뿌려주질 못하고, "null"을 뿌린다.
//             * 그러므로, 반드시 "String" 타입으로 설정하고, UUID 생성 시, 반드시 "*.toString()"으로 변환하여 저장해야 한다.
//             * 또한,
//             * Post.class.createAt 컬럼 옵션을 "@Builder.Default"로 주고, 값을 "LocalDateTime.now()"로 주면, 여기서 줄 필요가 없다.
//             */
//
//            String iStr = "";
//            for (int i = 1; i <= max; i++) {
//                // 3 방법: 제일 간단한 String.format() 사용
//                iStr = String.format("%04d", i);
//                System.err.println(getInfo() + " iStr: " + iStr);
//                // Post post = new Post(UUID.randomUUID(), "타이틀 " + 1, "컨텐츠 " + 1, "rwkang", LocalDateTime.now());
//                Post1 post1 = new Post1(
//                        (long) i,
//                        UUID.randomUUID().toString(),
//                        passwordEncoder.encode("0000"), /* import org.springframework.security.crypto.password.PasswordEncoder; 정리 후 사용 가능.*/
//                        // "0000",
//                        "타이틀 " + iStr,
//                        "컨텐츠 " + iStr,
//                        "rwkang@naver.com",
//                        LocalDateTime.now(),
//                        null);
//
//                post1List.add(post1);
//
//            }
//
////            List<Post1> post1List = List.of( // "개발 시에 데이터를 임시로 생성하여 넣어준다.
////                new Post1(UUID.randomUUID(), "타이틀 1", "컨텐츠 1", "rwkang", LocalDateTime.now()),
////                new Post1(UUID.randomUUID(), "타이틀 2", "컨텐츠 2", "rwkang", LocalDateTime.now()),
////                new Post1(UUID.randomUUID(), "타이틀 3", "컨텐츠 3", "rwkang", LocalDateTime.now())
////                );
//
//            System.err.println(getInfo() + " post1List: " + post1List);
//            post1Repository.saveAll(post1List);
//
//            //////////////////////////////////////////////////////////////////////////////////////////////
//
////            //----------------------------------------------------------------
////            아래와 같은, [user.build()] 메소드를 사용하여서도, 자료를 추가할 수 있다.
////            자바에서 리스트를 만드는 방식은 대표적으로 3가지 정도 존재한다.
////            1. 생성자[new ArrayList<>()]로 직접 리스트 객체를 인스턴화 시키는 것이고,
////               좀 더 간편하게 원소가 들은 리스트를 한방에 생성하기 위해 별도로
////               List<Number> arrayList = new ArrayList<>();
////               arrayList.add(1);
////               arrayList.add(2);
////               arrayList.add(3);
////            2. Arrays.asList() 와
////               List<Number> asList = Arrays.asList(1, 2, 3);
////            3. List.of() 메서드를 지원한다.
////               List<Number> listOf = List.of(1, 2, 3);
////
////            List<User> userList = List.of(
////                    User.builder().email("rwkang@naver.com")
////                            .password(passwordEncoder().encode("1111"))
////                            .name("rwkang")
////                            .userId("rwkang")
////                            .deptCode("1111")
////                            .empNo("0000")
////                            .phoneNo("0000")
////                            .profile("rwkang")
////                            .address("서울")
////                            .role("1")
////                            .updatedAt(LocalDateTime.now())
////                            .createdAt(LocalDateTime.now())
////                            .build()
////            );
////            userRepository.saveAll(userList);
////            //----------------------------------------------------------------
////
////            ////////////////////////////////////////////////////////////////////////////////////////////////
////
////            List<User1> user1List = new ArrayList<>();
////
////            /**
////             * UUID V4 : UUID.randomUUID().toString
////             * "UUID" 타입으로 직접 지정하면, 글자가 깨지는 현상이 발생하면서, MySQL DB에서는 제대로 뿌려주질 못하고, null을 뿌린다.
////             * 그러므로, 반드시 "String" 타입으로 설정하고, UUID 생성 시에도 반드시 ".toString()"으로 변환하여 저장해야 한다.
////             * 또한,
////             * Post.class.createdAt 컬럼을 @Builder.Default로 주고, 값을 LocalDateTime.now()로 주면, 여기서 줄 필요가 없다.
////             */
////
////            String iStr = "";
////            String deptCode = "";
////            for (int i = 1; i <= max; i++) {
////                iStr = String.format("%04d", i);
//////                deptCode = DeptCode.getDeptCode(max, i);
////                deptCode = DeptCode.getDeptCode(max, i);
////                User1 user1 = new User1(
////                        (long) i,
////                        UUID.randomUUID().toString(),
////                        "rwkang" + iStr + "@naver.com",
////                        "rwkang" + iStr,
//////                        applicationConfig.passwordEncoder().encode("0000"), /* 이렇게 [.java] 파일이 아닌, [class] 단위로도 [주입]을 할 수가 있네...*/
////                        passwordEncoder.encode("0000"), /* import org.springframework.security.crypto.password.PasswordEncoder; 정리 후 사용 가능.*/
//////                        "0000",
////                        "10011001" + iStr,
////                        "ROLE_USER",
////                        "0103333" + iStr,
////                        "서울 강서구 방화동 " + iStr,
////                        "프로파일 " + iStr,
////                        deptCode,
////                        LocalDateTime.now(),
////                        LocalDateTime.now());
////                user1List.add(user1);
////            }
////
////            System.err.println(getInfo() + " user1List: " + user1List);
////            user1Repository.saveAll(user1List);
//
//            ////////////////////////////////////////////////////////////////////////////////////////////////
//
//            List<Users1> users1List = new ArrayList<>();
//
//            /**
//             * 문자형 변수에 고정된 자릿수 만큼 부족한 숫자 값 앞에 "0" 붙히기
//             */
//            int iDigits = 0;
//            String deptCode = "";
//            for (int i = 1; i <= max; i++) {
//                // 1 방법: 먼저 숫자형 번수 값의 자릿수를 확인하고, 지정 자릿수와 비교하면서, 루프 돌면서 "0"을 채우는 방식
//                iDigits = (int) Math.log10(i) + 1;
//                System.err.println(getInfo() + " iDigits: " + iDigits);
//                for (int j = 0; j < 4 - iDigits; j++) {
//                    iStr += "0";
//                }
//                ;
//                System.err.println(getInfo() + " iStr: " + iStr);
//
//                // 2 방법: 3rt party 라이브러리 사용: 이건 안 되나???
//                // iStr = StringUtils.leftPad(String.valueOf(i), 4, '0');
//                // System.err.println(getInfo() + " iStr: " + iStr);
//
//                // 3 방법: 제일 간단한 String.format() 사용
//                iStr = String.format("%04d", i);
//                System.err.println(getInfo() + " iStr: " + iStr);
//
//                deptCode = getDeptCode(max, i);
//
//                Users1 users1 = new Users1(
//                        (long) i,
//                        UUID.randomUUID().toString(),
//                        "rwkang" + iStr + "@naver.com",
//                        "rwkang" + iStr + "@naver.com",
////                        applicationConfig.passwordEncoder().encode("0000"), /* 이렇게 [.java] 파일이 아닌, [class] 단위로도 [주입]을 할 수가 있네...*/
//                        passwordEncoder.encode("0000"), /* import org.springframework.security.crypto.password.PasswordEncoder; 정리 후 사용 가능.*/
////                        "0000",
//                        "10011001" + iStr,
//                        "ROLE_USER",
//                        "0103333" + iStr,
//                        "서울 강서구 방화동 " + iStr,
//                        "프로파일 " + iStr,
//                        deptCode,
//                        LocalDateTime.now(),
//                        LocalDateTime.now());
//                users1List.add(users1);
//            }
//
//            System.err.println(getInfo() + " users1List: " + users1List);
//            users1Repository.saveAll(users1List);
//
//            ////////////////////////////////////////////////////////////////////////////////////////////////
//
//            List<Member1> member1List = new ArrayList<>();
//
//            for (int i = 1; i <= max; i++) {
//                iStr = String.format("%04d", i);
//                deptCode = getDeptCode(max, i);
//
//                Member1 member1 = new Member1(
//                        (long) i,
//                        UUID.randomUUID().toString(),
//                        "rwkang" + iStr + "@naver.com",
//                        "rwkang" + iStr,
////                        applicationConfig.passwordEncoder().encode("0000"), /* 이렇게 [.java] 파일이 아닌, [class] 단위로도 [주입]을 할 수가 있네...*/
//                        passwordEncoder.encode("0000"), /* import org.springframework.security.crypto.password.PasswordEncoder; 정리 후 사용 가능.*/
////                        "0000",
//                        "10011001" + iStr,
//                        "ROLE_USER",
//                        "0103333" + iStr,
//                        "서울 강서구 방화동 " + iStr,
//                        "프로파일 " + iStr,
//                        deptCode,
//                        LocalDateTime.now(),
//                        LocalDateTime.now());
//
//                member1List.add(member1);
//
//            }
//
//            System.err.println(getInfo() + " member1List: " + member1List);
//            member1Repository.saveAll(member1List);
//
//            ////////////////////////////////////////////////////////////////////////////////////////////////
//
//            // 2023.12.12 Added. for Multiple Database Connections.
//            Random randomScore = new Random();
//            int scoreClass = randomScore.nextInt(100 - 65) + 65; // from 65 to 100
//            String subject;
//            Long studentId;
//            List<Score1> score1List = new ArrayList<>();
//
//            for (int i = 1; i <= scoreClass; i++) {
//                iStr = String.format("%04d", i);
//                if (i % 3 == 0) {
//                    subject = "math";
//                    studentId = 1L;
//                } else if (i % 3 == 1) {
//                    subject = "korean";
//                    studentId = 2L;
//                } else {
//                    subject = "english";
//                    studentId = 3L;
//                }
//                Score1 score1 = new Score1(
//                        (long) i,
//                        subject,
//                        scoreClass,
//                        studentId);
//                score1List.add(score1);
//            }
//
//            System.err.println(getInfo() + " score1List: " + score1List);
//            subjectScore1Repository.saveAll(score1List);
//
//
//            ////////////////////////////////////////////////////////////////////////////////////////////////
//
//            // 2023.12.12 Added. for Multiple Database Connections.
//            Random rndm = new Random();
//            int age = rndm.nextInt(60 - 30) + 30; // from 30 to 60
//            List<Student1> student1List = new ArrayList<>();
//            for (int i = 1; i <= max; i++) {
//                iStr = String.format("%04d", i);
//                Student1 student1 = new Student1(
//                        (long) i,
//                        "rwkang" + iStr,
//                        age + i);
//                student1List.add(student1);
//            }
//
//            System.err.println(getInfo() + " student1List: " + student1List);
//            student1Repository.saveAll(student1List);
//
//            ////////////////////////////////////////////////////////////////////////////////////////////////


            ////////////////////////////////////////////////////////////////////////////////////////////////

            /** 2024.02.28 Created. SpringBoot A 테이블 자료를 B 테이블로 그대로 복사, 붙혀넣기 하기. */

            List<Dept2> dept2List = dataCopyDeptService.copyService();
            System.err.println(getInfo() + " dept2List: " + dept2List.size());
            System.err.println(getInfo() + " dept2List: " + dept2List);


            List<Post2> post2List = dataCopyPostService.copyService();
            System.err.println(getInfo() + " post2List: " + post2List.size());
            System.err.println(getInfo() + " post2List: " + post2List);

            /* 2024.02.28 Conclusion. Oracle.오라클 DB에서는, "User" 테이블을 사용하면 에러 난다. */
//            List<User2> user2List = dataCopyUserService.user2List();
//            System.err.println(getInfo() + " user2List: " + user2List.size());

            List<Users2> users2List = dataCopyUsersService.copyService();
            System.err.println(getInfo() + " users2List: " + users2List.size());

//            List<Member2> member2List = dataCopyMemberService.member2List();
//            System.err.println(getInfo() + " member2List: " + member2List.size());

            List<Menu2> menu2List = dataCopyMenuService.copyService();
            System.err.println(getInfo() + " menu2List: " + menu2List.size());

        }

    }

}
