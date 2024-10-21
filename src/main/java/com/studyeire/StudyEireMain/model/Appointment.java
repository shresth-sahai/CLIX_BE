package com.studyeire.StudyEireMain.model;

import com.studyeire.StudyEireMain.constants.AppointmentType;
import com.studyeire.StudyEireMain.model.user.AppUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startDate;
    private int durationInMonths;
    private String notes;
    private AppointmentType appointmentType;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private AppUser student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private AppUser teacher;
}
