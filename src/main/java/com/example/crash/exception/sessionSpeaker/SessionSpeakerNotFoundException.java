package com.example.crash.exception.sessionSpeaker;

import com.example.crash.exception.ClientErrorException;
import org.springframework.http.HttpStatus;

public class SessionSpeakerNotFoundException  extends ClientErrorException {
    public SessionSpeakerNotFoundException() {
        super(HttpStatus.NOT_FOUND, "SessionSpeaker not fount");
    }

    public SessionSpeakerNotFoundException(Long speakerId) {
        super(HttpStatus.NOT_FOUND, "SessionSpeaker with name " + speakerId + " not found");
    }
}
