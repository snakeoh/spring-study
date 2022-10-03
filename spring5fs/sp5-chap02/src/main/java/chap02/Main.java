package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        // 1. 설정 정보를 이용해서 빈 객체를 생성한다.
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
            AppContext.class
        );

        // 2 빈 객체를 제공한다.
        Greeter g = ctx.getBean("greeter", Greeter.class);
        String msg = g.greet("스프링");
        System.out.println(msg);
        ctx.close();
    }
}
