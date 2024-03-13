package shop.onekorea.springboot2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.onekorea.springboot2.service.HelloService;

@RestController
//@Controller
public class HelloController {

    // 옛날 방식: @Autowired 사용 => 최근 방식(1. 생성자 사용 주입 방식, 2. Setter 사용 주입 방식) 중 생성자 사용 주입 방식 권장.
//    @Autowired
    final HelloService helloService;

    // 최근 방식: 생성자 사용
    public HelloController (HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello() {
        String hello = helloService.printHello();
        return hello;
    }

}
