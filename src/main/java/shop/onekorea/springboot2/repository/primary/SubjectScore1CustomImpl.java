//package shop.onekorea.springboot2.repository.primary;
//
//import com.querydsl.core.Tuple; // "jakarta.persistence.Tuple"이 아님에 특히 주의...
//import com.querydsl.jpa.impl.JPAQuery;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//import shop.onekorea.springboot2.dto.SubjectScoreResponseDto;
//
//import java.util.List;
//
//import static shop.onekorea.springboot2.Springboot2Application.getInfo;
//
//@RequiredArgsConstructor
//public class SubjectScore1CustomImpl implements SubjectScore1Custom {
//    private final JPAQueryFactory jpaQueryFactory;
//
//    @Override
//    public List<SubjectScoreResponseDto> findScoreByStudentId(Long studentId) {
//        System.err.println(getInfo() + " findScoreByStudentId.studentId: " + studentId);
//
//        /*
//        select st.student_id, st.name, st.age, st.subject, st.score
//            from student st
//            join score sc on st.student_id = sc.student_id
//            where st.student_id = 1
//            order by st.student_id;
//         */
//
//        // /src/main/generated/shop/onekorea/springboot2/entity/ Q Class 이용 => 해당 Q Class(QStudent, QScore) 파일을 직접 가서 확인할 것.
//        QStudent st = QStudent.student;
//        QScore sc = QScore.score1;
//
//        // 리턴 타입: "JAPQuery<Tuple>" 타입임에 주의.
//        JPAQuery<Tuple> jpaQueryTuple = jpaQueryFactory.select( st.studentId, st.name, st.age, sc.subject, sc.score)
//                .from(st)
//                .join(sc)
//                .on(st.studentId.eq(sc.studentId))
//                .where(st.studentId.eq(studentId))
//                .orderBy(st.studentId.asc());
//
//        // jpaQueryTuple.fetch(); // 이렇게 하면, "Entity" 쪽에 "lazy loading" 등의 특성을 타기 때문에, "fetchJoin().fetch()"를 사용한다.
//        // fetchJoin() 처리하면, "lazy loading"을 방지한다.
//
//        // https://goodteacher.tistory.com/642 참고
//        // 1. fetch(): 0개 이상의 데이터를 담아서 반환.
//        // 2. fetchOne(): 딱 1건일 경우에만 사용하며, 없으면 "null", 1건 이상이면, "NonUniqueResultException" 발생.
//        // 3. fetchFirst(): 1건 이상일 경우, 처음 결과.
//        // 4. fetchAll(): lazy loading.지연 로딩 없이, fetchJoin 형태로 변환.
//
//        List<Tuple> scoreListTuple = jpaQueryTuple.fetchJoin().fetch();
//
//        return scoreListTuple.stream().map(tuple -> SubjectScoreResponseDto.builder()
//                .studentId(tuple.get(st.studentId))
//                .name(tuple.get(st.name))
//                .age(tuple.get(st.age))
//                .subject(tuple.get(sc.subject))
//                .score(tuple.get(sc.score)).build()).toList(); // toList()는 "JDK 17" 이상에서만 사용 가능.
//
//    }
//}
