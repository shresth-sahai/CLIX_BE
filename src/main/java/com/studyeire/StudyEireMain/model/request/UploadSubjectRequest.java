package com.studyeire.StudyEireMain.model.request;

import com.studyeire.StudyEireMain.constants.SubjectDifficulty;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class UploadSubjectRequest {
    private Long belongsToSyllabus;
    private SubjectDifficulty difficulty;
    private String subjectName;
    private String description;
    private Set<Long> topics;
    private Set<Long> linkedTests;
}
