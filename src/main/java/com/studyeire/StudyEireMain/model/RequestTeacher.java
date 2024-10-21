package com.studyeire.StudyEireMain.model;

import com.studyeire.StudyEireMain.model.user.AppUser;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RequestTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentUsername;

    private String contactEmail;
    private String contactPhoneNumber;
    private String subjectDescription;
    private String meetingTimeDescription;

    private String parentUsername;
}
