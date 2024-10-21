package com.studyeire.StudyEireMain.exceptions.authexceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidUserUpdateRequest extends Exception {
    public InvalidUserUpdateRequest(String message) {
        super(message);
    }
}
