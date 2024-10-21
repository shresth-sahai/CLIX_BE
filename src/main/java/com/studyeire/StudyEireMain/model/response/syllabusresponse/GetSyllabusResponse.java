package com.studyeire.StudyEireMain.model.response.syllabusresponse;

import com.studyeire.StudyEireMain.constants.SyllabusType;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class GetSyllabusResponse {
    private String message;
    private Long syllabusId;
    private String syllabusName;
    private String description;
    private Set<SubjectResponseForSyllabus> subjects;
    private String createdBy;
    private SyllabusType type;
}
