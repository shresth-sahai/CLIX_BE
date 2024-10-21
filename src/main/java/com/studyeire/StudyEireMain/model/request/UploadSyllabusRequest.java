package com.studyeire.StudyEireMain.model.request;

import com.studyeire.StudyEireMain.constants.SyllabusType;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Builder
@Data
public class UploadSyllabusRequest {
    private String syllabusName;
    private String description;
    private Set<Long> subjects;
    private SyllabusType type;
}
