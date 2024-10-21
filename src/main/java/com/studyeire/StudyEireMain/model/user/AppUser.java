package com.studyeire.StudyEireMain.model.user;

import com.studyeire.StudyEireMain.constants.AccountType;
import com.studyeire.StudyEireMain.model.Address;
import com.studyeire.StudyEireMain.model.Appointment;
import com.studyeire.StudyEireMain.model.Institution;
import com.studyeire.StudyEireMain.model.academics.Assessment;
import com.studyeire.StudyEireMain.constants.UserRole;
import com.studyeire.StudyEireMain.model.academics.Syllabus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_user",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
        })
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution currentInstitute;

    @ManyToMany
    @JoinTable(
            name = "parent_student",
            joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<AppUser> children;
    @ManyToMany
    @JoinTable(
            name = "student_institution",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "institution_id")
    )
    protected List<Institution> previousInstitutes;

    @Column(nullable = false, unique = true)
    private String username;
    private String firstName;
    private String lastName;
    private String mobileNumber;

    // User Restriction fields
    private boolean expired;
    private boolean locked;
    private boolean credentialsExpired;
    private boolean enabled;
    // End User Restriction fields

    @Column(nullable = false, unique = true)
    private String email;
    private Date dob;
    private String password;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    private Set<UserRole> userRoles;

    @OneToMany(mappedBy = "createdByUser")
    private Set<Assessment> createdAssessments;
    @ManyToMany
    @JoinTable(
            name = "person_address",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Set<Address> addresses;

    @OneToMany(mappedBy = "student")
    private Set<Appointment> appointmentsAsStudent;

    @ManyToMany(mappedBy = "children")
    private Set<AppUser> parents;

    @OneToMany(mappedBy = "createdBy")
    private Set<Syllabus> syllabi;

    @OneToMany(mappedBy = "teacher")
    private Set<Appointment> appointmentsAsTeacher;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(userRoles == null)
            return authorities;
        for (UserRole role: userRoles) {
            authorities.add(new SimpleGrantedAuthority(role.name()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    // TODO: Handle race condition and concurrent update
    private void addRole(UserRole role) {
        if(userRoles == null){
            userRoles = new HashSet<>();
        }
        userRoles.add(role);
    }
}
