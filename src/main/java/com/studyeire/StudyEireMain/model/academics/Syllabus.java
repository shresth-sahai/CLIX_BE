package com.studyeire.StudyEireMain.model.academics;

import com.studyeire.StudyEireMain.constants.SyllabusType;
import com.studyeire.StudyEireMain.model.user.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Syllabus {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long syllabusId;

    @ManyToOne
    @JoinColumn( name = "student_id")
    private AppUser createdBy;

    private String syllabusName;

    @OneToMany(mappedBy = "belongsToSyllabus")
    private Set<Subject> subjects;

    @Enumerated(EnumType.STRING)
    private SyllabusType type;

    private String description;
}
