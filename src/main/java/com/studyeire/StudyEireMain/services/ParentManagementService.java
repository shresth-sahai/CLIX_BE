package com.studyeire.StudyEireMain.services;

import com.studyeire.StudyEireMain.model.response.StudentTestResultResponseParent;
import com.studyeire.StudyEireMain.model.response.StudentProgressResponseParent;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface ParentManagementService {
    public StudentProgressResponseParent getStudentProgress(String parentUsername, String studentUsername) throws UsernameNotFoundException;

    public StudentTestResultResponseParent getStudentTestResult(String parentUsername, String studentUsername) throws UsernameNotFoundException;
}
