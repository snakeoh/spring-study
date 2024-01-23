package spring;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

public class MemberPrinter {

    // public void print(Member member) {
    //     System.out.printf(
    //         "회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
    //         member.getId(),
    //         member.getEmail(),
    //         member.getName(),
    //         member.getRegisterDateTime()
    //     );
    // }

    @Autowired(required = false)
    private DateTimeFormatter dateTimeFormatter;

    // or
    @Autowired
    private Optional<DateTimeFormatter> formatterOpt;

    // or
    // @Autowired
    // @Nullable
    // private DateTimeFormatter formatterOpt;

    public void print(Member member) {
        // DateTimeFormatter dateTimeFormatter = formatterOpt.orElse(null);
        if (dateTimeFormatter == null) {
            System.out.printf(
                "회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
                member.getId(),
                member.getEmail(),
                member.getName(),
                member.getRegisterDateTime()
            );
        } else {
            System.out.printf(
                "회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n",
                member.getId(),
                member.getEmail(),
                member.getName(),
                dateTimeFormatter.format(member.getRegisterDateTime())
            );
        }
    }

    @Autowired(required = false) // 대상 빈이 존재하지 않으면 세터 매서드를 호출하지 않는다
    public void setDateFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
    // or
    // @Autowired
    // public void setDateFormatter(Optional<DateTimeFormatter> formmtterOpt) {
    //     if (formmtterOpt.isPresent()) {
    //         this.dateTimeFormatter = formmtterOpt.get();
    //     } else {
    //         this.dateTimeFormatter = null;
    //     }
    // }

    // or
    // @Autowired // 자동 주입할 빈이 존재하지 않아도 메서드가 호출된다.
    // public void setDateFormatter(@Nullable DateTimeFormatter dateTimeFormatter){
    //     this.dateTimeFormatter = dateTimeFormatter;
    // }
}
