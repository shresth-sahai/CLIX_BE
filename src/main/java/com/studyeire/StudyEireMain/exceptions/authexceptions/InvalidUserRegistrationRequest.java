package com.studyeire.StudyEireMain.exceptions.authexceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidUserRegistrationRequest extends Exception {
    public InvalidUserRegistrationRequest(String message) {
        super(message);
    }
}
