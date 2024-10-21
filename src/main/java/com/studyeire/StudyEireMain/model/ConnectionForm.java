package com.studyeire.StudyEireMain.model;

import com.studyeire.StudyEireMain.constants.ConnectionFormStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String description;

    @Enumerated(EnumType.STRING)
    private ConnectionFormStatus status;

    private Date createdOn;
}
