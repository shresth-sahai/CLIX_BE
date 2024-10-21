package com.studyeire.StudyEireMain.exceptions.authexceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidUserSigninRequest extends Exception {
    public InvalidUserSigninRequest(String message) {
        super(message);
    }
}
