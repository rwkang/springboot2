/**
 * 2024.02.02 Added. Strategy Design Pattern
 * https://www.youtube.com/watch?v=RAcFrxJcxP0&list=PLeMeDIV7bypsHwpa_8WaomU8N9VMW2ddV&index=5
 * => 08:55
 */

package shop.onekorea.springboot2.pattern;

// 2단계: Interface 사용 방식: strategypattern.drawio 파일 그림 참조
public class PayMoney implements PayMethod {
    @Override
    public String pay() {
        /*
        추가적인 로직은 여기서 작성한다.
         */
        return "현금으로 지급";
    }
}

// 1단계: String 사용 방식: strategypattern.drawio 파일 그림 참조
//public class PayMoney {
//    public String pay() {
//        /*
//        추가적인 로직은 여기서 작성한다.
//         */
//        return "현금으로 지급";
//    }
//}
