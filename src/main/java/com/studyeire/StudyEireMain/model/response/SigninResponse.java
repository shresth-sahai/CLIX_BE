package com.studyeire.StudyEireMain.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SigninResponse {
    private String username;
    private String token;
}
