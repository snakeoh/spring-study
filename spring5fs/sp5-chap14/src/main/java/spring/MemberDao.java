package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

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
                    rs.getTimestamp("REGDATE").toLocalDateTime());
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
                                rs.getTimestamp("REGDATE").toLocalDateTime());
                        member.setId(rs.getLong("ID"));
                        return member;
                    }
                },
                email);

        // 람다
        /*
         * List<Member> result = jdbcTemplate.query(
         * "select * from MEMBER where EMAIL = ?",
         * (ResultSet rs, int rowNum) -> {
         * 
         * Member member = new Member(
         * rs.getString("EMAIL"),
         * rs.getString("PASSWORD"),
         * rs.getString("NAME"),
         * rs.getTimestamp("REGDATE").toLocalDateTime());
         * member.setId(rs.getLong("ID"));
         * return member;
         * },
         * email);
         * }
         */

        // 동일한 RowMapper 구현을 여러 곳에서 사용한다면 RowMapper 인터페이스를 구현한 클래스를 만들어서 코드 중복 방지
        // MemberRowMapper 객체 생성
        // List<Member> result = jdbcTemplate.query(
        // "select * from MEMBER where EMAIL = ? and NAME = ?",
        // new MemberRowMapper(),
        // email, name);

        return result.isEmpty() ? null : result.get(0);
    }

    public void insert(Member member) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con)
                            throws SQLException {
                        PreparedStatement pstmt = con.prepareStatement(
                                "insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) " +
                                        "values (?, ?, ?, ?)",
                                new String[] { "ID" });
                        pstmt.setString(1, member.getEmail());
                        pstmt.setString(2, member.getPassword());
                        pstmt.setString(3, member.getName());
                        pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
                        return pstmt;
                    }
                },
                keyHolder);
        Number keyValue = keyHolder.getKey();
        member.setId(keyValue.longValue());
        // 람다
        // jdbcTemplate.update((Connection con) -> {
        // PreparedStatement pstmt = con.prepareStatement(
        // "insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE)" +
        // "values (?, ?, ?, ?)",
        // new String[] {"ID"}
        // );
        // pstmt.setString(1, member.getEmail());
        // pstmt.setString(2, member.getPassword());
        // pstmt.setString(3, member.getName());
        // pstmt.setTimestamp(4, new
        // Timestamp(member.getRegisterDateTime().getLong(null)));
        // return pstmt;
        // }, keyHolder);
    }

    public void update(Member member) {
        jdbcTemplate.update(
                "update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?",
                member.getName(),
                member.getPassword(),
                member.getEmail());
        // PreparedStatementCreator를 이용한 쿼리 실행
        // jdbcTemplate.update(new PreparedStatementCreator() {
        // @Override
        // public PreparedStatement createdPreparedStatement(Connection con) throws
        // SQLException{
        // // 파라미터로 전달받은 Connection을 이용해서 PreparedStatement 생성
        // PreparedStatement pstmt = con.prepareStatement(
        // "insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) values (?, ?, ?, ?)"
        // );
        // // 인덱스의 파라미터 값 설정
        // pstmt.setString(1, member.getEmail());
        // pstmt.setString(2, member.getPassword());
        // pstmt.setString(3, member.getName());
        // pstmt.setString(4, Timestamp.valueOf(member.getRegisterDateTime()));
        // // 생성한 PreparedStatement 객체 리턴
        // return pstmt;
        // }
        // });
    }

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
                                rs.getTimestamp("REGDATE").toLocalDateTime());
                        member.setId(rs.getLong("ID"));
                        return member;
                    }
                });
        return results;
    }

    // ==
    // public Collection<Member> selectAll(){
    // List<Member> results = jdbcTemplate.query("select * from MEMBER",
    // new MemberRowMapper());
    // return results;
    // }

    public int count() {
        Integer count = jdbcTemplate.queryForObject("select count(8) from MEMBER", Integer.class);
        return count;
    }

    public List<Member> selectByRegdate(
            LocalDateTime from, LocalDateTime to) {
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER where REGDATE between ? and ? " +
                        "order by REGDATE desc",
                new RowMapper<Member>() {
                    @Override
                    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Member member = new Member(
                                rs.getString("EMAIL"),
                                rs.getString("PASSWORD"),
                                rs.getString("NAME"),
                                rs.getTimestamp("REGDATE").toLocalDateTime());
                        member.setId(rs.getLong("ID"));
                        return member;
                    }
                },
                from, to);
        return results;
    }
}
