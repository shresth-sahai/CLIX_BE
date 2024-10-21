package com.studyeire.StudyEireMain.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserSigninRequest {
    // Use either username
    private String username;
    // Or email
    private String email;
    private String password;
}
