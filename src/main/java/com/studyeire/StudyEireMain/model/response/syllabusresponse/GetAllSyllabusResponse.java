package com.studyeire.StudyEireMain.model.response.syllabusresponse;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class GetAllSyllabusResponse {
    private Set<GetSyllabusResponse> syllabi;
    private String message;
}
