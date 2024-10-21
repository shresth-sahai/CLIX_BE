package com.studyeire.StudyEireMain.model.response;

import com.studyeire.StudyEireMain.constants.ConnectionFormStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class ConnectionFormResponse {
    private Long id;
    private String name;
    private String email;
    private String description;
    private ConnectionFormStatus status;
    private Date createdOn;
}
