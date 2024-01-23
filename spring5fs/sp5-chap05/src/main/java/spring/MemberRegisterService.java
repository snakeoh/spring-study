package spring;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberRegisterService {

    @Autowired
    private MemberDao memberDao;

    public MemberRegisterService() {}

    // 생성자를 통해 의존 객체를 주입 받음
    public MemberRegisterService(MemberDao memberDao) {
        // 주입 받은 객체를 필드에 할당
        this.memberDao = memberDao;
    }

    public Long regist(RegisterRequest req) {
        // 이메일로 회원 데이터(Member) 조회
        // 주입받은 의존 객체의 매서드를 사용
        Member member = memberDao.selectByEmail(req.getEmail());
        if (member != null) {
            // 같은 이메일을 가진 회원이 이미 존재하면 익셉션 발생
            throw new DuplicateMemberException("dup email " + req.getEmail());
        }
        // 같은 이메일을 가진 회원이 존재하지 않으면 DB 삽입
        Member newMember = new Member(
            req.getEmail(),
            req.getPassword(),
            req.getName(),
            LocalDateTime.now()
        );
        memberDao.insert(newMember);
        return newMember.getId();
    }
}
