package hello.boot;

import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan // 컴포넌트 스캔 기능이 추가된 애노테이션
public @interface MySpringBootApplication { }
