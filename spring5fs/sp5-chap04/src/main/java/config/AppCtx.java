package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;
import spring.VersionPrinter;

@Configuration
public class AppCtx {

    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }

    @Bean
    public MemberRegisterService memberRegSvc() {
        return new MemberRegisterService();
    }

    @Bean
    public ChangePasswordService changePwdSvc() {
        // ChangePasswordService pwdSvc = new ChangePasswordService();
        // pwdSvc.setMemberDao(memberDao());
        // return pwdSvc;
        return new ChangePasswordService();
    }

    // 6.2에서 추가
    @Bean
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }

    @Bean
    public MemberListPrinter listPrinter() {
        // return new MemberListPrinter(memberDao(), memberPrinter());
        return new MemberListPrinter();
    }

    @Bean
    public MemberInfoPrinter infoPrinter() {
        // MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
        // infoPrinter.setMemberDao(memberDao());
        // infoPrinter.setPrinter(memberPrinter());
        // return infoPrinter;
        return new MemberInfoPrinter();
    }

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
