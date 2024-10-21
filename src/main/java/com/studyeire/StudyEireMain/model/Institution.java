package com.studyeire.StudyEireMain.model;

import com.studyeire.StudyEireMain.model.user.AppUser;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "previousInstitutes")
    private List<AppUser> alumni;

    @OneToMany(mappedBy = "currentInstitute")
    private List<AppUser> currentStudents;

    private String institutionName;
}
