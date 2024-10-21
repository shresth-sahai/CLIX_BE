package com.studyeire.StudyEireMain.model.response;

import com.studyeire.StudyEireMain.constants.AccountType;
import com.studyeire.StudyEireMain.constants.UserRole;
import com.studyeire.StudyEireMain.model.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.util.Pair;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private Date dob;
    private Pair<Long, String> currentInstitute;
    private boolean expired;
    private boolean locked;
    private boolean credentialsExpired;
    private boolean enabled;
    private AccountType accountType;
    private Set<Address> addresses;
    private Set<String> parents;
    private Set<String> children;
    private Set<UserRole> roles;
    private String message;
}
