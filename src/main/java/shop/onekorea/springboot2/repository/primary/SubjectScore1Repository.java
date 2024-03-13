/** QueryDSL 사용...
 * https://www.youtube.com/watch?v=pLfUiXbVOh8&list=PLeMeDIV7bypvyxWv7eIUZubmfx9W-Jjdg&index=8
 * QueryDSL 사용 실험을 위한 Repository. 특이점은, "ScoreRepository"가 아니고, "SubjectScoreRepository"라는 점이다.
 * QueryDSL 사용을 위해서는, "QueryDSL Interface"를 상속 받아 사용할 수 있게끔, "QueryDSL" 용 "Interface"를 추가로 만들어서 사용한다.
 *  1. 기본적인 "CRUD" 기능은, "JPA(JpaRepository)"를 상속 받아 처리하고,
 *  2. 복잡한 "조인 쿼리"에 대한 것들만 "QueryDSL"을 이용하여 처리하는데,
 *  3. "Interface"만 [다중 상속]이 가능하므로, 먼저 "Interface(SubjectScoreCustom.java)" 파일을 만들어,
 *      ===> service 파일에서 사용할 method(findScoreByStudentId())를 [단지 정의만] 하고,
 *  4. 그것으로 구현(Implements)한 "Class(SubjectScoreCustomImpl.java)" 파일을 만든다.
 *      ===> 여기에 실제 사용되는 쿼리(QueryDSL)에 해당하는 method(findScoreByStudentId())가 정리되어 있다.
 * @author: 2023.12.02 by rwkang.
 */
package shop.onekorea.springboot2.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.onekorea.springboot2.entity.primary.Score1;

public interface SubjectScore1Repository extends JpaRepository<Score1, Long>, SubjectScore1Custom {
//public interface SubjectScoreRepository extends JpaRepository<Score, Long> {
}
