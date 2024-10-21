package com.studyeire.StudyEireMain.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegisterResponse {
    private String username;
    private String token;
}
