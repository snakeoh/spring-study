package spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MemberDao {

    private JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // RowMapper를 구현한 클래스를 작성
    public class MemberRowMapper implements RowMapper<Member> {

        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member(
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"),
                rs.getString("NAME"),
                rs.getTimestamp("REGDATE").toLocalDateTime()
            );
            member.setId(rs.getLong("ID"));
            return member;
        }
    }

    public Member selectByEmail(String email) {
        List<Member> result = jdbcTemplate.query(
            "select * from MEMBER where EMAIL = ?",
            new RowMapper<Member>() {
                @Override
                public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Member member = new Member(
                        rs.getString("EMAIL"),
                        rs.getString("PASSWORD"),
                        rs.getString("NAME"),
                        rs.getTimestamp("REGDATE").toLocalDateTime()
                    );
                    member.setId(rs.getLong("ID"));
                    return member;
                }
            },
            email
        );

        // 람다
        /*        List<Member> result = jdbcTemplate.query(
            "select * from MEMBER where EMAIL = ?",
            (ResultSet rs, int rowNum) -> {

                Member member = new Member(
                    rs.getString("EMAIL"), 
                    rs.getString("PASSWORD"),
                    rs.getString("NAME"),
                    rs.getTimestamp("REGDATE").toLocalDateTime());
                member.setId(rs.getLong("ID"));
                return member;
            },
            email);
        }
        */

        // 동일한 RowMapper 구현을 여러 곳에서 사용한다면 RowMapper 인터페이스를 구현한 클래스를 만들어서 코드 중복 방지
        // MemberRowMapper 객체 생성
        // List<Member> result = jdbcTemplate.query(
        //     "select * from MEMBER where EMAIL = ? and NAME = ?",
        //     new MemberRowMapper(),
        //     email, name);

        return result.isEmpty() ? null : result.get(0);
    }

    public void insert(Member member) {}

    public void update(Member member) {}

    // public Collection<Member> selectAll() {
    public List<Member> selectAll() {
        List<Member> results = jdbcTemplate.query(
            "select * from MEMBER",
            new RowMapper<Member>() {
                @Override
                public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Member member = new Member(
                        rs.getString("EMAIL"),
                        rs.getString("PASSWORD"),
                        rs.getString("NAME"),
                        rs.getTimestamp("REGDATE").toLocalDateTime()
                    );
                    member.setId(rs.getLong("ID"));
                    return member;
                }
            }
        );
        return results;
    }
    // ==
    // public Collection<Member> selectAll(){
    //     List<Member> results = jdbcTemplate.query("select * from MEMBER",
    //     new MemberRowMapper());
    //     return results;
    // }
}
