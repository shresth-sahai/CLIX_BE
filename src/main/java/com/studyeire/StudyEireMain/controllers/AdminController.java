package com.studyeire.StudyEireMain.controllers;

import com.studyeire.StudyEireMain.exceptions.authexceptions.InvalidUserUpdateRequest;
import com.studyeire.StudyEireMain.model.request.UserUpdateRequest;
import com.studyeire.StudyEireMain.model.response.*;
import com.studyeire.StudyEireMain.services.AdminManagementService;
import com.studyeire.StudyEireMain.services.UserManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import static com.studyeire.StudyEireMain.constants.ConstantStrings.API_V1_ADMIN;

@RestController
@RequestMapping(API_V1_ADMIN)
@RequiredArgsConstructor
public class AdminController {

    private final AdminManagementService adminManagementService;
    private final UserManagementService userManagementService;

    @PostMapping("/{adminUsername}/students/{studentUsername}/teachers/{teacherUsername}/assign-teacher")
    public ResponseEntity<AssignTeacherForStudentResponse> assignTeacherForStudent(@PathVariable String adminUsername,
                                                                                   @PathVariable String studentUsername,
                                                                                   @PathVariable String teacherUsername) {
        try {
            AssignTeacherForStudentResponse assignTeacherForStudentResponse = adminManagementService.assignTeacherForStudent(adminUsername, studentUsername, teacherUsername);
            return new ResponseEntity<>(assignTeacherForStudentResponse, HttpStatus.OK);
        }
        catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(AssignTeacherForStudentResponse.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(AssignTeacherForStudentResponse.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{adminUsername}/users/{userName}/contact-user")
    public ResponseEntity<ContactUserResponse> contactUser(@PathVariable String adminUsername,
                                                           @PathVariable String username) {
        try {
            ContactUserResponse contactUserResponse = adminManagementService.contactUser(adminUsername, username);
            return new ResponseEntity<>(contactUserResponse, HttpStatus.OK);
        }
        catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(ContactUserResponse.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(ContactUserResponse.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{adminUsername}/users")
    public ResponseEntity<ContactUserResponse> viewAllUsers(@PathVariable String adminUsername,
                                                           @PathVariable String username) {
        try {
            ContactUserResponse contactUserResponse = adminManagementService.contactUser(adminUsername, username);
            return new ResponseEntity<>(contactUserResponse, HttpStatus.OK);
        }
        catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(ContactUserResponse.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(ContactUserResponse.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // TODO: Implement update user service
    @PutMapping("/{adminUser}/users/{userName}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserUpdateRequest request) {
        try {
            UserResponse userResponse = userManagementService.updateUser(request);
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
        catch (InvalidUserUpdateRequest ex) {
            return new ResponseEntity<>(UserResponse.builder().message(ex.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(UserResponse.builder().message(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{adminUser}/users/{userName}")
    public ResponseEntity<UserDeleteResponse> deleteUser(@PathVariable String username) {
        try {
            UserDeleteResponse userDeleteResponse = userManagementService.deleteUser(username);
            return new ResponseEntity<>(userDeleteResponse, HttpStatus.NO_CONTENT);
        }
        catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(UserDeleteResponse.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.NOT_FOUND);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(UserDeleteResponse.builder().message(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
