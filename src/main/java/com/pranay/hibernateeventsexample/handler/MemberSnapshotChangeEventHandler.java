package com.pranay.hibernateeventsexample.handler;

import com.pranay.hibernateeventsexample.model.Member;
import com.pranay.hibernateeventsexample.readmodel.MemberSnapshotRepository;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MemberSnapshotChangeEventHandler implements PostChangeEventHandler {
    private static final List<String> types = Arrays.asList("DELETE", "INSERT");
    private final MemberSnapshotRepository memberSnapshotRepository;

    public MemberSnapshotChangeEventHandler(MemberSnapshotRepository memberSnapshotRepository) {
        this.memberSnapshotRepository = memberSnapshotRepository;
    }

    @Override
    public void run(EntityChangeEvent event) {
        if (!(event.getEntity() instanceof Member) || !types.contains(event.getType())){
            return;
        }
        memberSnapshotRepository.save(((Member) event.getEntity()).snapshot());
        throw new RuntimeException();
    }
}
