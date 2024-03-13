package shop.onekorea.springboot2.dto;

/* jdk 17 이상, [record: 특정 컬럼(들) 지정] 키워드 사용 가능 */
public record AuthResponseDto(String token) {
}
