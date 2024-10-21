package com.studyeire.StudyEireMain.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDeleteResponse {
    private String username;
    private boolean isDeleted;
    private String message;
}
