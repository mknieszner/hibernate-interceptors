package com.pranay.hibernateeventsexample.readmodel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MemberSnapshotRepository {
    private final MemberSnapshotContentRepository repository;

    public MemberSnapshotRepository(MemberSnapshotContentRepository repository) {
        this.repository = repository;
    }

    public Long save(MemberSnapshot snapshot) {
        MemberSnapshotContent memberSnapshotContent = new MemberSnapshotContent();
        memberSnapshotContent.setSnapshotId(snapshot.getId());
        memberSnapshotContent.setContent(this.toString(snapshot));
        this.deleteAllBySnapshotId(snapshot.getId());
        return this.repository.save(memberSnapshotContent).getSnapshotId();
    }

    public void deleteAllBySnapshotId(Long id) {
        this.repository.deleteAllBySnapshotId(id);
    }


    public Optional<MemberSnapshot> findById(Long memberId) {
        return this.repository.findById(memberId)
                .map(MemberSnapshotContent::getContent)
                .map(this::toObject);
    }

    public List<MemberSnapshot> findAll() {
        List<MemberSnapshotContent> all = this.repository.findAll();
        return all.stream()
                .map(MemberSnapshotContent::getContent)
                .map(this::toObject).
                collect(Collectors.toList());
    }

    private MemberSnapshot toObject(String content) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(content, MemberSnapshot.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String toString(MemberSnapshot snapshot) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(snapshot);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}

