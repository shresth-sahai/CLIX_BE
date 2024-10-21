package com.studyeire.StudyEireMain.model.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class UploadTopicRequest {
    private Long belongsToSubject;
    private String topicHeading;
    private String description;
    private Set<Long> linkedTests;
}
