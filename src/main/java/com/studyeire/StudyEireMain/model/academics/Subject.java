package com.studyeire.StudyEireMain.model.academics;

import com.studyeire.StudyEireMain.constants.SubjectDifficulty;
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
public class Subject {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long subjectId;

    @ManyToOne
    @JoinColumn(name = "syllabus_id")
    private Syllabus belongsToSyllabus;

    @Enumerated(EnumType.STRING)
    private SubjectDifficulty difficulty;

    @ManyToMany(mappedBy = "linkedSubjects")
    private Set<Assessment> assessments;

    @OneToMany(mappedBy = "subject")
    private Set<Topic> topics;

    private String subjectName;

    private String description;
}
