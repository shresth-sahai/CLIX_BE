package com.studyeire.StudyEireMain.model.response.syllabusresponse;

import com.studyeire.StudyEireMain.constants.SyllabusType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SyllabusUploadResponse {
    private String message;
    private Long syllabusId;
    private String syllabusName;
    private String syllabusDescription;
    private String createdByUser;
    private SyllabusType syllabusType;
}
