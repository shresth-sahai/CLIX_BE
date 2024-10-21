package com.studyeire.StudyEireMain.services;

import com.studyeire.StudyEireMain.model.response.AssignTeacherForStudentResponse;
import com.studyeire.StudyEireMain.model.response.ContactUserResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

public interface AdminManagementService {
    public AssignTeacherForStudentResponse assignTeacherForStudent(String adminUsername, String studentUsername, String teacherUsername) throws UsernameNotFoundException;

    public ContactUserResponse contactUser(String adminUsername, String username) throws UsernameNotFoundException;
}
