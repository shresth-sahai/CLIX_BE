package com.studyeire.StudyEireMain.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookTeacherRequest {
    private String contactEmail;
    private String contactPhoneNumber;
    private String subjectDescription;
    private String meetingTimeDescription;
    private String parentUsername;
}
