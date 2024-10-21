package com.studyeire.StudyEireMain.controllers;

import com.studyeire.StudyEireMain.exceptions.teachermanagementexceptions.InvalidSyllabusCompleteRequest;
import com.studyeire.StudyEireMain.model.response.*;
import com.studyeire.StudyEireMain.model.response.syllabusresponse.SyllabusCompleteResponse;
import com.studyeire.StudyEireMain.services.TeacherManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import static com.studyeire.StudyEireMain.constants.ConstantStrings.API_V1_TEACHERS;

@RestController
@RequestMapping(API_V1_TEACHERS)
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherManagementService teacherManagementService;

    // Get Students for a teacher
    @GetMapping("/{teacherUsername}/students")
    public ResponseEntity<StudentsForTeacherResponse> getStudentsForTeacher(@PathVariable String teacherUsername) {
        try {
            StudentsForTeacherResponse studentsForTeacher = teacherManagementService.getStudentsForTeacher(teacherUsername);
            return new ResponseEntity<>(studentsForTeacher, HttpStatus.OK);
        }
        catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(StudentsForTeacherResponse.builder().message(ex.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex){
            return new ResponseEntity<>(StudentsForTeacherResponse.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Mark Syllabus Completed
    @PutMapping("/{teacherUsername}/students/{studentUsername}/syllabus/{syllabusId}/complete")
    public ResponseEntity<SyllabusCompleteResponse> markSyllabusComplete(@PathVariable String teacherUsername,
                                                                         @PathVariable String studentUsername,
                                                                         @PathVariable Long syllabusId) {
        try{
            SyllabusCompleteResponse syllabusCompleteResponse = teacherManagementService.markSyllabusComplete(teacherUsername, studentUsername, syllabusId);
            return new ResponseEntity<>(syllabusCompleteResponse, HttpStatus.OK);
        }
        catch (InvalidSyllabusCompleteRequest ex) {
            return new ResponseEntity<>(SyllabusCompleteResponse.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(SyllabusCompleteResponse.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Mark topic complete
    @PutMapping("/{teacherUsername}/students/{studentUsername}/syllabus/{syllabusId}/topics/{topicId}/complete")
    public ResponseEntity<TopicCompleteResponse> markTopicComplete(@PathVariable String teacherUsername,
                                                                   @PathVariable String studentUsername,
                                                                   @PathVariable String syllabusId,
                                                                   @PathVariable String topicId) {
        try{
            TopicCompleteResponse topicCompleteResponse = teacherManagementService.markTopicComplete(teacherUsername,
                    studentUsername,
                    syllabusId,
                    topicId);
            return new ResponseEntity<>(topicCompleteResponse, HttpStatus.OK);
        }
        catch (UsernameNotFoundException ex){
            return new ResponseEntity<>(TopicCompleteResponse.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        }
        catch(Exception e){
            return new ResponseEntity<>(TopicCompleteResponse.builder()
                    .message(e.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // TODO: Use to track students syllabus completion, tests and test results
    @PostMapping("/{teacherUsername}/students/{studentUsername}/test")
    public ResponseEntity<CreateTestResponse> createTest(@PathVariable String teacherUsername,
                                                         @PathVariable String studentUsername) {
        try {
            CreateTestResponse createTestResponse = teacherManagementService.createTest(teacherUsername, studentUsername);
            return new ResponseEntity<>(createTestResponse, HttpStatus.OK);
        }
        catch (UsernameNotFoundException ex){
            return new ResponseEntity<>(CreateTestResponse.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(CreateTestResponse.builder().message(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{teacherUsername}/students/{studentUsername}/test-results")
    public ResponseEntity<TestResultResponse> getTestResults(@PathVariable String teacherUsername,
                                                             @RequestBody String studentUsername) {
        try {
            TestResultResponse testResultResponse = teacherManagementService.getTestResults(teacherUsername, studentUsername);
            return new ResponseEntity<>(testResultResponse, HttpStatus.OK);
        }
        catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(TestResultResponse.builder().message(ex.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex){
            return new ResponseEntity<>(TestResultResponse.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
