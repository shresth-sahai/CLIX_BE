package com.studyeire.StudyEireMain.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRegisterResponse {
    private String username;
    private String token;
    private String message;
}
