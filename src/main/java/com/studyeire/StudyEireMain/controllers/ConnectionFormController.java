package com.studyeire.StudyEireMain.controllers;

import com.studyeire.StudyEireMain.model.request.ConnectionFormRequest;
import com.studyeire.StudyEireMain.model.response.ConnectionFormResponse;
import com.studyeire.StudyEireMain.services.ConnectionFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.studyeire.StudyEireMain.constants.ConstantStrings.*;

@RestController
@RequestMapping(API_V1)
@RequiredArgsConstructor
public class ConnectionFormController {

    private final ConnectionFormService connectionFormService;

    // GET Requests

    @GetMapping(API_CONNECTION_FORM)
    public ResponseEntity<List<ConnectionFormResponse> > getAllConnectionForms(@RequestParam MultiValueMap<String, String> filter){
        return connectionFormService.getAllConnectionForms(filter);
    }

    @GetMapping(API_CONNECTION_FORM_WITH_ID)
    public ResponseEntity<ConnectionFormResponse> getConnectionForm(@PathVariable Long id){
        return connectionFormService.getConnectionForm(id);
    }

    // POST Requests

    @PostMapping(API_CONNECTION_FORM)
    public ResponseEntity<ConnectionFormResponse> createConnectionForm(@RequestBody ConnectionFormRequest connectionFormRequest){
        return connectionFormService.createConnectionForm(connectionFormRequest);
    }

    // PATCH Requests

    @PatchMapping(API_CONNECTION_FORM_RESOLVE)
    public ResponseEntity<String> resolveConnectionForm(@PathVariable Long id) {
        return connectionFormService.resolveConnectionForm(id);
    }
}
