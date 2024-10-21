package com.studyeire.StudyEireMain.exceptions.studentmanagementexceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidBookTeacherRequest extends Exception {
    public InvalidBookTeacherRequest(String message) {
        super(message);
    }
}
