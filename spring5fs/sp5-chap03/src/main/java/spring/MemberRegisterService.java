import java.time.LocalDateTime;

public class MemberRegisterService {
    private MemberDao memberDao = new MemberDao();

    public void regist(RegisterRequest req) {
        // 이메일로 회원 데이터(Member) 조회
        Member member = memberDao.selectByEmail(req.getEamil());
        if (member != null) {
            // 같은 이메일을 가진 회원이 이미 존재하면 익셉션 발생
            throw new DuplicateMemberException("dup email " +  req.getEamil());
        }
        // 같은 이메일을 가진 회원이 존재하지 않으면 DB 삽입
        Member newMember = new Member(
            req.getEamil(), req.getPassword(), req.getName(),
            LocalDateTime.now());
        memberDao.insert(newMember);
    }
}
