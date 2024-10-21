package com.studyeire.StudyEireMain.model.request;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String username;
    private String email;
    private String password;
    private boolean isEnabled;
    // TODO: Implement other fields of user entity here
}
