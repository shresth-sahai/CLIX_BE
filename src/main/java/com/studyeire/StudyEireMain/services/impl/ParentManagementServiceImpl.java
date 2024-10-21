package com.studyeire.StudyEireMain.services.impl;

import com.studyeire.StudyEireMain.model.response.StudentTestResultResponseParent;
import com.studyeire.StudyEireMain.model.response.StudentProgressResponseParent;
import com.studyeire.StudyEireMain.services.ParentManagementService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ParentManagementServiceImpl implements ParentManagementService {
    @Override
    public StudentProgressResponseParent getStudentProgress(String parentUsername, String studentUsername) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public StudentTestResultResponseParent getStudentTestResult(String parentUsername, String studentUsername) throws UsernameNotFoundException {
        return null;
    }
}
