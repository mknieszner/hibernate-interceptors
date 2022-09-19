package com.pranay.hibernateeventsexample.handler;

import com.pranay.hibernateeventsexample.model.Member;
import com.pranay.hibernateeventsexample.readmodel.MemberSnapshotRepository;
import com.pranay.hibernateeventsexample.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SnapshotRegenerator {
    private final MemberSnapshotRepository memberSnapshotRepository;
    private final MemberRepository memberRepository;

    public SnapshotRegenerator(MemberSnapshotRepository memberSnapshotRepository, MemberRepository memberRepository) {
        this.memberSnapshotRepository = memberSnapshotRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void regenerate() {
        List<Member> all = this.memberRepository.findAll();
        all.stream()
                .map(Member::snapshot)
                .forEach(memberSnapshotRepository::save);
        System.out.println("Regenarated for: " + all.size() +" items.");
    }
}
