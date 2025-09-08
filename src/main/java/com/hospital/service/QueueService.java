package com.hospital.service;

import com.hospital.model.Doctor;
import com.hospital.model.QueueEntry;
import com.hospital.repository.QueueEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueueService {

    private final QueueEntryRepository queueRepository;

    public List<QueueEntry> getQueueByDoctor(Doctor doctor) {
        return queueRepository.findByDoctorOrderByJoinedAtAsc(doctor);
    }

    public QueueEntry saveQueueEntry(QueueEntry entry) {
        return queueRepository.save(entry);
    }

    public void removeQueueEntry(Long id) {
        queueRepository.deleteById(id);
    }
}
