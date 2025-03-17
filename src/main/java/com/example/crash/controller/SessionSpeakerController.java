package com.example.crash.controller;

import com.example.crash.model.sessionspeaker.SessionSpeaker;
import com.example.crash.model.sessionspeaker.SessionSpeakerPatchRequestBody;
import com.example.crash.model.sessionspeaker.SessionSpeakerPostRequestBody;
import com.example.crash.service.SessionSpeakerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/session-speakers")
public class SessionSpeakerController {

    @Autowired private SessionSpeakerService sessionSpeakerService;

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<SessionSpeaker>> getSessionSpeakers() {
        var sessionSpeakers = sessionSpeakerService.getSessionSpeakers();

        return ResponseEntity.ok(sessionSpeakers);
    }

    // 스피커 아이디 기반으로 단건 조회
    @GetMapping("/{speakerId}")
    public ResponseEntity<SessionSpeaker> getSessionSpeakersBySpeakerId(@PathVariable Long speakerId) {
        var sessionSpeaker = sessionSpeakerService.getSessionSpeakersBySpeakerId(speakerId);
        return ResponseEntity.ok(sessionSpeaker);
    }

    // 생성
    @PostMapping
    public ResponseEntity<SessionSpeaker> createSessionSpeaker(@Valid @RequestBody SessionSpeakerPostRequestBody sessionSpeakerPostRequestBody) {
        var sessionSpeakers = sessionSpeakerService.createSessionSpeaker(sessionSpeakerPostRequestBody);

        return ResponseEntity.ok(sessionSpeakers);
    }
    // 생성
    @PatchMapping("/{speakerId}")
    public ResponseEntity<SessionSpeaker> updateSessionSpeaker(@PathVariable Long speakerId, @RequestBody SessionSpeakerPatchRequestBody sessionSpeakerPatchRequestBody) {
        var sessionSpeakers = sessionSpeakerService.updateSessionSpeaker(speakerId,sessionSpeakerPatchRequestBody);

        return ResponseEntity.ok(sessionSpeakers);
    }
    // 생성
    @DeleteMapping("/{speakerId}")
    public ResponseEntity<Void> deleteSessionSpeaker(@PathVariable Long speakerId) {
        sessionSpeakerService.deleteSessionSpeaker(speakerId);

        return ResponseEntity.noContent().build();
    }

}
