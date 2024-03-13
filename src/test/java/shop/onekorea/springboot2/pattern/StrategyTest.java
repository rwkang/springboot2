/**
 * 2024.02.02 Added. Strategy Design Pattern
 * https://www.youtube.com/watch?v=RAcFrxJcxP0&list=PLeMeDIV7bypsHwpa_8WaomU8N9VMW2ddV&index=5
 * => 08:55
 */

package shop.onekorea.springboot2.pattern;

import org.junit.jupiter.api.Test;
import shop.onekorea.springboot2.service.PurchaseService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StrategyTest {

    // 2단계: Interface 사용 방식: strategypattern.drawio 파일 그림 참조
    @Test
    public void strategyText() throws Exception {
        PurchaseService service = new PurchaseService();

        String result = service.pay(new PayCard());
        assertEquals("카드로 지급", result);

        String result2 = service.pay(new PayMoney());
        assertEquals("현금으로 지급", result2);

        String result3 = service.pay(new PayApple());
        assertEquals("애플페이로 지급", result3);

        // 예) 페이사 추가된 경우, PaySamsung() 클래스 파일 추가.
        String result4 = service.pay(new PaySamsung());
        assertEquals("삼성페이로 지급", result4);



    }

    // 1단계: String 사용 방식: strategypattern.drawio 파일 그림 참조
//    @Test
//    public void legacyPayTest() throws Exception {
//        PurchaseService service = new PurchaseService();
//
//        String result = service.pay("card");
//        assertEquals("카드로 지급", result);
//
//        String result2 = service.pay("money");
//        assertEquals("현금으로 지급", result2);
//
//        String result3 = service.pay("apple");
//        assertEquals("애플페이로 지급", result3);
//    }

}
