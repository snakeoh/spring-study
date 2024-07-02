package controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import spring.DuplicateMemberException;
import spring.Member;
import spring.MemberDao;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class RestMemberController {

    private MemberDao memberDao;
    private MemberRegisterService registerService;

    @GetMapping("/api/members")
    public List<Member> members() {
        return memberDao.selectAll();
    }

    // @PostMapping("/api/members")
    // public void newMember(
    // @RequestBody @Valid RegisterRequest regReq,
    // HttpServletResponse response) throws IOException {
    // try {
    // Long newMemberId = registerService.regist(regReq);
    // response.setHeader("Location", "/api/members/" + newMemberId);
    // response.setStatus(HttpServletResponse.SC_CREATED);
    // } catch (DuplicateMemberException dupEx) {
    // response.sendError(HttpServletResponse.SC_CONFLICT);
    // }
    // }

    @PostMapping("/api/members")
    public ResponseEntity<Object> newMember(
            @RequestBody @Valid RegisterRequest regReq) {
        try {
            Long newMemberId = registerService.regist(regReq);
            URI uri = URI.create("/api/members/" + newMemberId);
            return ResponseEntity.created(uri).build();
        } catch (DuplicateMemberException dupEx) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    // @GetMapping("/api/members/{id}")
    // public Member member(@PathVariable Long id,
    // HttpServletResponse response) throws IOException {
    // Member member = memberDao.selectById(id);
    // if (member == null) {
    // response.sendError(HttpServletResponse.SC_NOT_FOUND);
    // }
    // return member;
    // }

    // @GetMapping("/api/members/{id}")
    // public ResponseEntity<Object> member(@PathVariable Long id) {
    // Member member = memberDao.selectById(id);
    // if (member == null) {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND)
    // .body(new ErrorResponse("no member"));
    // }
    // return ResponseEntity.status(HttpStatus.OK).body(member);
    // }

    @GetMapping("/api/members/{id}")
    public Member member(@PathVariable Long id) {
        Member member = memberDao.selectById(id);
        if (member == null) {
            throw new MemberNotFoundException();
        }
        return member;
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoData() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("no member"));
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void setRegisterService(MemberRegisterService registerService) {
        this.registerService = registerService;
    }
}
