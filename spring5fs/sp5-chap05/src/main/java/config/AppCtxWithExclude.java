package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;
import spring.MemberSummaryPrinter;
import spring.VersionPrinter;

@Configuration
@ComponentScan(
    basePackages = { "spring" },
    excludeFilters = @Filter(type = FilterType.REGEX, pattern = "spring\\..*Dao")
)
// AspectJ 패턴
// @ComponentScan(basePackages = { "spring" },
//     excludeFilters = @Filter(type = FilterType.ASPECTJ, pattern = "spring.*Dao"))

// 애노테이션 클래스 특정
// @ComponentScan(
//     basePackages = { "spring", "spring2" },
//     excludeFilters = @Filter(
//         type = FilterType.ANNOTATION,
//         classes = { NoProduct.class, ManualBean.class }
//     )
// )

// 특정 타입이나 그 하위 타입
// @ComponentScan(
//     basePackages = { "spring" },
//     excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = MemberDao.class)
// )

// 다중 필터
// @ComponentScan(
//     basePackages = { "spring" },
//     excludeFilters = {
//         @Filter(type = FilterType.ANNOTATION, classes = ManualBean.class),
//         @Filter(type = FilterType.REGEX, pattern = "spring2\\..*")
//     }
// )
public class AppCtxWithExclude {

    // @Bean
    // public MemberDao memberDao() {
    //     return new MemberDao();
    // }

    // @Bean
    // public MemberRegisterService memberRegSvc() {
    //     return new MemberRegisterService();
    // }

    // @Bean
    // public ChangePasswordService changePwdSvc() {
    //     // ChangePasswordService pwdSvc = new ChangePasswordService();
    //     // pwdSvc.setMemberDao(memberDao());
    //     // return pwdSvc;
    //     return new ChangePasswordService();
    // }

    // @Bean
    // public MemberPrinter memberPrinter() {
    //     return new MemberPrinter();
    // }

    @Bean
    @Qualifier("printer")
    public MemberPrinter memberPrinter1() {
        return new MemberPrinter();
    }

    @Bean
    @Qualifier("summaryPrinter")
    public MemberSummaryPrinter memberPrinter2() {
        return new MemberSummaryPrinter();
    }

    // Autowired 설정으로 의존 주입 시 결국은 메서드 이름이 아니라 클래스로 구분해서 넣는다?

    // @Bean
    // public MemberListPrinter listPrinter() {
    //     // return new MemberListPrinter(memberDao(), memberPrinter());
    //     return new MemberListPrinter();
    // }

    // @Bean
    // public MemberInfoPrinter infoPrinter() {
    //     MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
    //     // infoPrinter.setMemberDao(memberDao());
    //     infoPrinter.setPrinter(memberPrinter2());
    //     return infoPrinter;
    //     // return new MemberInfoPrinter();
    // }

    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }

    @Bean
    public VersionPrinter oldVersionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(4);
        versionPrinter.setMinorVersion(3);
        return versionPrinter;
    }
}
