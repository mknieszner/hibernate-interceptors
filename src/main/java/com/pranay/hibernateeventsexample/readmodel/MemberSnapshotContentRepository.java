package com.pranay.hibernateeventsexample.readmodel;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface MemberSnapshotContentRepository extends JpaRepository<MemberSnapshotContent, Long> {
    void deleteAllBySnapshotId(Long id);
}