/**
 * 2024.02.02 Added. Strategy Design Pattern
 * https://www.youtube.com/watch?v=RAcFrxJcxP0&list=PLeMeDIV7bypsHwpa_8WaomU8N9VMW2ddV&index=5
 * => 08:55
 */

package shop.onekorea.springboot2.service;

import shop.onekorea.springboot2.pattern.PayMethod;

public class PurchaseService {

    // 2단계 Interface 사용 방식
    public String pay(PayMethod payMethod) { // 2단계

        String result = payMethod.pay();

        return result;

    }

    // 1단계 String 사용 방식.
//    public String pay(String payMethod) { // 1단계
//
//        String result = "";
//        switch(payMethod) {
//            case "card":
//                // 1. 일반적인 코딩
//                // result = "카드로 지급";
//
//                // 2. Strategy design pattern을 활용하여 코딩: strategypattern.drawio 파일 그림 참조
//                PayCard card = new PayCard();
//                result = card.pay();
//                break;
//            case "money":
//                // result = "현금으로 지급";
//                PayMoney money = new PayMoney();
//                result = money.pay();
//                break;
//            case "apple":
//                // result = "애플페이로 지급";
//                PayApple apple = new PayApple();
//                result = apple.pay();
//                break;
//            default:
//                break;
//        }
//
//        return result;
//
//    }

}
