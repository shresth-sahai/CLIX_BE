package com.studyeire.StudyEireMain.services.impl;

import com.studyeire.StudyEireMain.model.response.AssignTeacherForStudentResponse;
import com.studyeire.StudyEireMain.model.response.ContactUserResponse;
import com.studyeire.StudyEireMain.services.AdminManagementService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AdminManagementServiceImpl implements AdminManagementService {
    @Override
    public AssignTeacherForStudentResponse assignTeacherForStudent(String adminUsername, String studentUsername, String teacherUsername) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public ContactUserResponse contactUser(String adminUsername, String username) throws UsernameNotFoundException {
        return null;
    }
}
