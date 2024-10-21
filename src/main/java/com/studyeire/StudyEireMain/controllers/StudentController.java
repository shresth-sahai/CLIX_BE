package com.studyeire.StudyEireMain.controllers;

import com.studyeire.StudyEireMain.exceptions.studentmanagementexceptions.InvalidBookTeacherRequest;
import com.studyeire.StudyEireMain.exceptions.studentmanagementexceptions.InvalidSyllabusUploadRequest;
import com.studyeire.StudyEireMain.model.request.BookTeacherRequest;
import com.studyeire.StudyEireMain.model.request.UploadSyllabusRequest;
import com.studyeire.StudyEireMain.model.response.*;
import com.studyeire.StudyEireMain.model.response.syllabusresponse.GetAllSyllabusResponse;
import com.studyeire.StudyEireMain.model.response.syllabusresponse.SyllabusUploadResponse;
import com.studyeire.StudyEireMain.services.StudentManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import static com.studyeire.StudyEireMain.constants.ConstantStrings.*;

@RestController
@RequestMapping(API_V1_STUDENTS)
@RequiredArgsConstructor
public class StudentController {

    private final StudentManagementService studentManagementService;

    @PostMapping("/{username}/request-teacher")
    public ResponseEntity<BookTeacherResponse> bookTeacher(@PathVariable String studentUsername, @RequestBody BookTeacherRequest request) {
        try {
            BookTeacherResponse userRegisterResponse = studentManagementService.bookTeacher(studentUsername, request);
            return new ResponseEntity<>(userRegisterResponse, HttpStatus.OK);
        }
        catch (InvalidBookTeacherRequest ex) {
            return new ResponseEntity<>(BookTeacherResponse.builder().message(ex.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex){
            return new ResponseEntity<>(BookTeacherResponse.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{username}/syllabus")
    public ResponseEntity<SyllabusUploadResponse> uploadSyllabus(@PathVariable String username, @RequestBody UploadSyllabusRequest request) {
        try{
            SyllabusUploadResponse syllabusUploadResponse = studentManagementService.uploadSyllabus(username, request);
            return new ResponseEntity<>(syllabusUploadResponse, HttpStatus.OK);
        }
        catch (InvalidSyllabusUploadRequest ex) {
            return new ResponseEntity<>(SyllabusUploadResponse.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(SyllabusUploadResponse.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{username}/syllabus")
    public ResponseEntity<GetAllSyllabusResponse> getStudentSyllabus(@PathVariable String username) {
        try{
            GetAllSyllabusResponse getStudentSyllabusResponse = studentManagementService.getAllSyllabus(username);
            return new ResponseEntity<>(getStudentSyllabusResponse, HttpStatus.OK);
        }
        catch (UsernameNotFoundException ex){
            return new ResponseEntity<>(GetAllSyllabusResponse.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        }
        catch(Exception e){
            return new ResponseEntity<>(GetAllSyllabusResponse.builder()
                    .message(e.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // TODO: Use to track students syllabus completion, tests and test results
    @GetMapping("/{username}/progress")
    public ResponseEntity<StudentProgressResponse> getStudentProgress(@PathVariable String username) {
        try {
            StudentProgressResponse studentProgressResponse = studentManagementService.getStudentProgress(username);
            return new ResponseEntity<>(studentProgressResponse, HttpStatus.OK);
        }
        catch (UsernameNotFoundException ex){
            return new ResponseEntity<>(StudentProgressResponse.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(StudentProgressResponse.builder().message(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
