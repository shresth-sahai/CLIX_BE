package com.studyeire.StudyEireMain.services;

import com.studyeire.StudyEireMain.exceptions.studentmanagementexceptions.InvalidSyllabusUploadRequest;
import com.studyeire.StudyEireMain.model.request.BookTeacherRequest;
import com.studyeire.StudyEireMain.model.request.UploadSyllabusRequest;
import com.studyeire.StudyEireMain.model.response.*;
import com.studyeire.StudyEireMain.model.response.syllabusresponse.GetAllSyllabusResponse;
import com.studyeire.StudyEireMain.model.response.syllabusresponse.SyllabusUploadResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface StudentManagementService {
    public BookTeacherResponse bookTeacher(String username, BookTeacherRequest request) throws Exception;

    public SyllabusUploadResponse uploadSyllabus(String username, UploadSyllabusRequest request) throws InvalidSyllabusUploadRequest;

    public GetAllSyllabusResponse getAllSyllabus(String username) throws UsernameNotFoundException;

    public StudentProgressResponse getStudentProgress(String username) throws UsernameNotFoundException;
}