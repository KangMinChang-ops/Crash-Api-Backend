package com.example.crash.model.sessionspeaker;

import com.example.crash.model.entity.SessionSpeakerEntity;

public record SessionSpeaker(Long speakerId, String company, String name, String description, String profile) {
    public static SessionSpeaker from(SessionSpeakerEntity sessionSpeakerEntity) {
        return new SessionSpeaker(
                sessionSpeakerEntity.getSpeakerId(),
                sessionSpeakerEntity.getCompany(),
                sessionSpeakerEntity.getDescription(),
                sessionSpeakerEntity.getName(),
                sessionSpeakerEntity.getProfile()
        );
    }
}
