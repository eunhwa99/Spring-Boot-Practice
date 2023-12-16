package hello.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/hello-spring")
    public String hello() {
        System.out.println("MyController.hello");
        return "hello spring!";
    }

}
