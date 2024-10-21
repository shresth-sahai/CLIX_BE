package com.studyeire.StudyEireMain.model.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class UserRegisterRequest {
    private String username;
    private String email;
    private String password;
    // TODO: Implement other fields of user entity here
}
