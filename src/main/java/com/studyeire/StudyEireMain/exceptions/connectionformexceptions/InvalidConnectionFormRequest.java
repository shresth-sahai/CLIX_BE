package com.studyeire.StudyEireMain.exceptions.connectionformexceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidConnectionFormRequest extends Exception {
    public InvalidConnectionFormRequest(String message) {
        super(message);
    }
}
