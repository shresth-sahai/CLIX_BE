package com.studyeire.StudyEireMain.services.impl;

import com.studyeire.StudyEireMain.exceptions.teachermanagementexceptions.InvalidSyllabusCompleteRequest;
import com.studyeire.StudyEireMain.model.response.*;
import com.studyeire.StudyEireMain.model.response.syllabusresponse.SyllabusCompleteResponse;
import com.studyeire.StudyEireMain.services.TeacherManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherManagementServiceImpl implements TeacherManagementService {
    @Override
    public StudentsForTeacherResponse getStudentsForTeacher(String teacherUsername) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public TopicCompleteResponse markTopicComplete(String teacherUsername, String studentUsername, String syllabusId, String topicId) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public CreateTestResponse createTest(String teacherUsername, String studentUsername) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public SyllabusCompleteResponse markSyllabusComplete(String teacherUsername, String studentUsername, Long syllabusId) throws InvalidSyllabusCompleteRequest {
        return null;
    }

    @Override
    public TestResultResponse getTestResults(String teacherUsername, String studentUsername) throws UsernameNotFoundException {
        return null;
    }
}
