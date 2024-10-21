package com.studyeire.StudyEireMain.model.response.syllabusresponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubjectResponseForSyllabus {
    private String subjectName;
    private Long subjectId;
}
