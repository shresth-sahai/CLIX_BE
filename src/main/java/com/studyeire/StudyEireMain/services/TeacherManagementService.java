package com.studyeire.StudyEireMain.services;

import com.studyeire.StudyEireMain.exceptions.teachermanagementexceptions.InvalidSyllabusCompleteRequest;
import com.studyeire.StudyEireMain.model.response.*;
import com.studyeire.StudyEireMain.model.response.syllabusresponse.SyllabusCompleteResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface TeacherManagementService {

    public StudentsForTeacherResponse getStudentsForTeacher(String teacherUsername) throws UsernameNotFoundException;

    public TopicCompleteResponse markTopicComplete(String teacherUsername, String studentUsername, String syllabusId, String topicId)
            throws UsernameNotFoundException;

    public CreateTestResponse createTest(String teacherUsername, String studentUsername) throws UsernameNotFoundException;

    public SyllabusCompleteResponse markSyllabusComplete(String teacherUsername, String studentUsername, Long syllabusId) throws InvalidSyllabusCompleteRequest;

    public TestResultResponse getTestResults(String teacherUsername, String studentUsername) throws UsernameNotFoundException;
}
