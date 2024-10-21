package com.studyeire.StudyEireMain.model.request;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UploadAssessmentRequest {
    private Set<Long> linkedTopics;
    private Set<Long> linkedSubjects;
    private Long createdByUser;
}
