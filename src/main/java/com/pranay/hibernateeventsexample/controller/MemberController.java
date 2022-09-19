package com.pranay.hibernateeventsexample.controller;

import com.pranay.hibernateeventsexample.exception.ResourceNotFoundException;
import com.pranay.hibernateeventsexample.handler.SnapshotRegenerator;
import com.pranay.hibernateeventsexample.model.Member;
import com.pranay.hibernateeventsexample.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MemberController {
    private final MemberRepository memberRepository;
    private final SnapshotRegenerator snapshotRegenerator;

    @Autowired
    public MemberController(MemberRepository memberRepository, SnapshotRegenerator snapshotRegenerator) {
        this.memberRepository = memberRepository;
        this.snapshotRegenerator = snapshotRegenerator;
    }

    @Deprecated
    @GetMapping("/members/original")
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @PostMapping("/members/snapshots")
    public ResponseEntity<Void> generateSnapshots() {
        snapshotRegenerator.regenerate();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/members")
    public Member createMember(@Valid @RequestBody Member member) {
        return memberRepository.save(member);
    }

    @PutMapping("/members/{id}")
    @Transactional
    public ResponseEntity<Void> updateMember(@PathVariable(value = "id") Long memberId,
                                             @Valid @RequestBody Member memberDetails) throws ResourceNotFoundException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found for this id :: " + memberId));

        member.setEmailId(memberDetails.getEmailId());
        member.setLastName(memberDetails.getLastName());
        member.setFirstName(memberDetails.getFirstName());

        return ResponseEntity.ok().build();
    }
}
