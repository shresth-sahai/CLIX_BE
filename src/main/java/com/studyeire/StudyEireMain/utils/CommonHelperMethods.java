package com.studyeire.StudyEireMain.utils;

import com.studyeire.StudyEireMain.model.academics.Syllabus;
import com.studyeire.StudyEireMain.model.response.syllabusresponse.GetSyllabusResponse;
import com.studyeire.StudyEireMain.model.response.syllabusresponse.SubjectResponseForSyllabus;

import java.util.stream.Collectors;

public class CommonHelperMethods {
    public static boolean isNullOrEmpty(String val) {
        if(val == null)
            return true;
        if(val.isBlank())
            return true;
        return false;
    }
    public static GetSyllabusResponse convertSyllabusToSyllabusResponse(Syllabus syllabus, String message) {
        return GetSyllabusResponse.builder()
                .syllabusName(syllabus.getSyllabusName())
                .syllabusId(syllabus.getSyllabusId())
                .description(syllabus.getDescription())
                .createdBy(syllabus.getCreatedBy() == null ? "" : syllabus.getCreatedBy().getUsername())
                .type(syllabus.getType())
                .subjects(syllabus.getSubjects().parallelStream().map(subject ->
                                SubjectResponseForSyllabus.builder()
                                        .subjectId(subject.getSubjectId())
                                        .subjectName(subject.getSubjectName())
                                        .build())
                        .collect(Collectors.toSet()))
                .message(message)
                .build();
    }
}
