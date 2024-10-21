package com.studyeire.StudyEireMain.services;

import com.studyeire.StudyEireMain.exceptions.authexceptions.InvalidUserRegistrationRequest;
import com.studyeire.StudyEireMain.exceptions.authexceptions.InvalidUserSigninRequest;
import com.studyeire.StudyEireMain.exceptions.authexceptions.InvalidUserUpdateRequest;
import com.studyeire.StudyEireMain.model.request.UserRegisterRequest;
import com.studyeire.StudyEireMain.model.request.UserSigninRequest;
import com.studyeire.StudyEireMain.model.request.UserUpdateRequest;
import com.studyeire.StudyEireMain.model.response.UserDeleteResponse;
import com.studyeire.StudyEireMain.model.response.UserRegisterResponse;
import com.studyeire.StudyEireMain.model.response.UserResponse;
import com.studyeire.StudyEireMain.model.response.UserSigninResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserManagementService {
    public UserRegisterResponse registerUser(UserRegisterRequest request) throws InvalidUserRegistrationRequest;
    public UserSigninResponse signinUser(UserSigninRequest request) throws InvalidUserSigninRequest;
    public UserResponse getUser(String username) throws UsernameNotFoundException;

    public UserResponse updateUser(UserUpdateRequest request) throws InvalidUserUpdateRequest;

    public UserDeleteResponse deleteUser(String username) throws UsernameNotFoundException;
}
