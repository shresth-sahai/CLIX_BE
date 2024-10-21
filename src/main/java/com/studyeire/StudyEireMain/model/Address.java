package com.studyeire.StudyEireMain.model;

import com.studyeire.StudyEireMain.model.user.AppUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "addresses")
    List<AppUser> users;

    private String zipCode;
    private String city;
    private String country;
    private String county;
    private String addressLine;
}
