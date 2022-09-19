package com.pranay.hibernateeventsexample.handler;

import com.pranay.hibernateeventsexample.model.Member;
import com.pranay.hibernateeventsexample.readmodel.MemberSnapshotRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MemberSnapshotDeletedEventHandler implements PostChangeEventHandler {
    private final MemberSnapshotRepository memberSnapshotRepository;

    public MemberSnapshotDeletedEventHandler(MemberSnapshotRepository memberSnapshotRepository) {
        this.memberSnapshotRepository = memberSnapshotRepository;
    }

    @Override
    public void run(EntityChangeEvent event) {
        if (!(event.getEntity() instanceof Member) || !Objects.equals(event.getType(), "DELETE")) {
            return;
        }
        memberSnapshotRepository.deleteAllBySnapshotId(((Member) event.getEntity()).snapshot().getId());
    }
}
