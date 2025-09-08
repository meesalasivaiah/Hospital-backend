package com.hospital.controller;

import com.hospital.model.Doctor;
import com.hospital.model.QueueEntry;
import com.hospital.service.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/queue")
@RequiredArgsConstructor
public class QueueController {

    private final QueueService queueService;

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<QueueEntry>> getQueueByDoctor(@PathVariable Long doctorId) {
        Doctor doctor = Doctor.builder().id(doctorId).build();
        return ResponseEntity.ok(queueService.getQueueByDoctor(doctor));
    }

    @PostMapping
    public ResponseEntity<QueueEntry> addToQueue(@RequestBody QueueEntry entry) {
        return ResponseEntity.ok(queueService.saveQueueEntry(entry));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeFromQueue(@PathVariable Long id) {
        queueService.removeQueueEntry(id);
        return ResponseEntity.ok("Queue entry removed successfully");
    }
}
