package com.studyeire.StudyEireMain.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SigninRequest {
    // Use either username
    private String username;
    // Or email
    private String email;
    private String password;
}
