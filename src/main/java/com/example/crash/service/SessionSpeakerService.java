package com.example.crash.service;

import com.example.crash.exception.sessionSpeaker.SessionSpeakerNotFoundException;
import com.example.crash.model.entity.SessionSpeakerEntity;
import com.example.crash.model.sessionspeaker.SessionSpeaker;
import com.example.crash.model.sessionspeaker.SessionSpeakerPatchRequestBody;
import com.example.crash.model.sessionspeaker.SessionSpeakerPostRequestBody;
import com.example.crash.repository.SessionSpeakerEntityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class SessionSpeakerService {
    @Autowired private SessionSpeakerEntityRepository sessionSpeakerEntityRepository;

    public List<SessionSpeaker> getSessionSpeakers() {
        var sessionSpeakerEntities = sessionSpeakerEntityRepository.findAll();
        return sessionSpeakerEntities.stream().map(SessionSpeaker::from).toList();
    }

    public SessionSpeakerEntity getSessionSpeakerEntityById(Long speakerId) {
        return  sessionSpeakerEntityRepository
                .findById(speakerId)
                .orElseThrow(
                        () -> new SessionSpeakerNotFoundException(speakerId)
                );

    }
    public SessionSpeaker getSessionSpeakersBySpeakerId(Long speakerId) {
        var SessionSpeakerEntity = getSessionSpeakerEntityById(speakerId);
        return SessionSpeaker.from(SessionSpeakerEntity);
    }

    public SessionSpeaker createSessionSpeaker(SessionSpeakerPostRequestBody sessionSpeakerRequestBody) {
        var sessionSpeakerEntity =
        SessionSpeakerEntity.of(
                sessionSpeakerRequestBody.company(),
                sessionSpeakerRequestBody.name(),
                sessionSpeakerRequestBody.description()
        );
        return SessionSpeaker.from(sessionSpeakerEntityRepository.save(sessionSpeakerEntity));
    }

    public SessionSpeaker updateSessionSpeaker(Long speakerId, SessionSpeakerPatchRequestBody sessionSpeakerPatchRequestBody) {
        var sessionSpeakerEntity = getSessionSpeakerEntityById(speakerId);
        if(!ObjectUtils.isEmpty(sessionSpeakerPatchRequestBody.company())){
            sessionSpeakerEntity.setCompany(sessionSpeakerPatchRequestBody.company());
        }
        if(!ObjectUtils.isEmpty(sessionSpeakerPatchRequestBody.name())){
            sessionSpeakerEntity.setName(sessionSpeakerPatchRequestBody.name());
        }
        if(!ObjectUtils.isEmpty(sessionSpeakerPatchRequestBody.description())){
            sessionSpeakerEntity.setDescription(sessionSpeakerPatchRequestBody.description());
        }
        
        return SessionSpeaker.from(sessionSpeakerEntityRepository.save(sessionSpeakerEntity));
    }

    public void deleteSessionSpeaker(Long speakerId) {
        var sessionSpeakerEntity = getSessionSpeakerEntityById(speakerId);
        sessionSpeakerEntityRepository.delete(sessionSpeakerEntity);
    }
}
