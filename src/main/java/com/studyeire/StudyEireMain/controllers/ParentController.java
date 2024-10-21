package com.studyeire.StudyEireMain.controllers;

import com.studyeire.StudyEireMain.model.response.StudentProgressResponseParent;
import com.studyeire.StudyEireMain.model.response.StudentTestResultResponseParent;
import com.studyeire.StudyEireMain.services.ParentManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import static com.studyeire.StudyEireMain.constants.ConstantStrings.API_V1_PARENTS;

@RestController
@RequestMapping(API_V1_PARENTS)
@RequiredArgsConstructor
public class ParentController {
    private final ParentManagementService parentManagementService;

    @GetMapping("/{parentUsername}/students/{studentUserName}/progress")
    public ResponseEntity<StudentProgressResponseParent> getStudentProgressForParent(@PathVariable String parentUsername, @PathVariable String studentUsername) {
        try {
            StudentProgressResponseParent studentProgressResponseParent = parentManagementService.getStudentProgress(parentUsername, studentUsername);
            return new ResponseEntity<>(studentProgressResponseParent, HttpStatus.OK);
        }
        catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(StudentProgressResponseParent.builder().message(ex.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex){
            return new ResponseEntity<>(StudentProgressResponseParent.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{parentUsername}/students/{studentUsername}/test-results")
    public ResponseEntity<StudentTestResultResponseParent> getStudentTestResultsForParent(@PathVariable String parentUsername, @PathVariable String studentUsername) {
        try {
            StudentTestResultResponseParent studentTestResultResponseParent = parentManagementService.getStudentTestResult(parentUsername, studentUsername);
            return new ResponseEntity<>(studentTestResultResponseParent, HttpStatus.OK);
        }
        catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(StudentTestResultResponseParent.builder().message(ex.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex){
            return new ResponseEntity<>(StudentTestResultResponseParent.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
