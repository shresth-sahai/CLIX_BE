package com.studyeire.StudyEireMain.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSigninResponse {
    private String username;
    private String token;
    private String message;
}
