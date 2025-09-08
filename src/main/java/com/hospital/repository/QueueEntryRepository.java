package com.hospital.repository;

import com.hospital.model.QueueEntry;
import com.hospital.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueueEntryRepository extends JpaRepository<QueueEntry, Long> {
    List<QueueEntry> findByDoctorOrderByJoinedAtAsc(Doctor doctor);
}
