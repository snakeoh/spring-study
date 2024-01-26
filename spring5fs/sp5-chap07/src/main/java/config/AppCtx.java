package config;

import aspect.ExeTimeAspect;
import chap07.Calculator;
import chap07.RecCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
// @EnableAspectJAutoProxy
// 빈 객체가 인터페이스를 상속할 때 인터페이스가 아니 클래스를 이용해서 프록시를 생성하고 싶다면 아래처럼
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppCtx {

    @Bean
    public ExeTimeAspect exeTimeAspect() {
        return new ExeTimeAspect();
    }

    // 설정 클래스:
    // AOP 적용 시 RecCalaulator가 상속받은 Calculator 인터페이스를 이용해서 프로시 생성
    @Bean
    public Calculator calculator() {
        return new RecCalculator();
    }
}
