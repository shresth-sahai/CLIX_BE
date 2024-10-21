package com.studyeire.StudyEireMain.services;

import com.studyeire.StudyEireMain.exceptions.syllabusexceptions.SyllabusNotFound;
import com.studyeire.StudyEireMain.model.response.syllabusresponse.GetSyllabusResponse;

public interface SyllabusManagementService {
    public GetSyllabusResponse getSyllabus(Long syllabusId) throws SyllabusNotFound;
}
