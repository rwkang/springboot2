package shop.onekorea.springboot2.pattern;

public class PaySamsung implements PayMethod {
    @Override
    public String pay() {
        /*
        추가적인 로직은 여기서 작성한다.
         */
        return "삼성페이로 지급";
    }
}
