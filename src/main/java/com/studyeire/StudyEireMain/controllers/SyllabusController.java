package com.studyeire.StudyEireMain.controllers;

import com.studyeire.StudyEireMain.model.response.syllabusresponse.GetSyllabusResponse;
import com.studyeire.StudyEireMain.services.SyllabusManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.studyeire.StudyEireMain.constants.ConstantStrings.API_V1_SYLLABUS;

@RestController
@RequestMapping(API_V1_SYLLABUS)
@RequiredArgsConstructor
public class SyllabusController {

    private final SyllabusManagementService syllabusManagementService;
    public ResponseEntity<GetSyllabusResponse> getSyllabus(@PathVariable Long syllabusId) {
        try{
            GetSyllabusResponse getSyllabusResponse = syllabusManagementService.getSyllabus(syllabusId);
            return new ResponseEntity<>(getSyllabusResponse, HttpStatus.OK);
        }
        catch (UsernameNotFoundException ex){
            return new ResponseEntity<>(GetSyllabusResponse.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity<>(GetSyllabusResponse.builder()
                    .message(e.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
