package com.pranay.hibernateeventsexample.readmodel;

import com.pranay.hibernateeventsexample.exception.ResourceNotFoundException;
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
public class MemberSnapshotController {
    private final MemberSnapshotRepository memberSnapshotRepository;

    @Autowired
    public MemberSnapshotController(MemberSnapshotRepository memberSnapshotRepository) {
        this.memberSnapshotRepository = memberSnapshotRepository;
    }

    @GetMapping("/members")
    public List<MemberSnapshot> getAllMembers() {
        return memberSnapshotRepository.findAll();
    }


    @GetMapping("/members/{id}")
    public ResponseEntity<MemberSnapshot> getMemberById(@PathVariable(value = "id") Long memberId)
            throws ResourceNotFoundException {
        MemberSnapshot member = memberSnapshotRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + memberId));
        return ResponseEntity.ok().body(member);
    }
}
