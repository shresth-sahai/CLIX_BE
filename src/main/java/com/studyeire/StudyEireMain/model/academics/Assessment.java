package com.studyeire.StudyEireMain.model.academics;

import com.studyeire.StudyEireMain.model.user.AppUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assessmentId;

    @ManyToMany
    @JoinTable(
            name = "assessment_subject",
            joinColumns = @JoinColumn(name = "assessment_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> linkedSubjects;

    @ManyToMany
    @JoinTable(
            name = "assessment_topic",
            joinColumns = @JoinColumn(name = "assessment_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private Set<Topic> linkedTopics;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser createdByUser;
}
