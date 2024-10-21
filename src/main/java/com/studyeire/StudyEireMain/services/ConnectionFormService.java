package com.studyeire.StudyEireMain.services;

import com.studyeire.StudyEireMain.model.request.ConnectionFormRequest;
import com.studyeire.StudyEireMain.model.response.ConnectionFormResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface ConnectionFormService {
    public ResponseEntity<List<ConnectionFormResponse> > getAllConnectionForms(MultiValueMap <String,String> filter);
    public ResponseEntity<ConnectionFormResponse> getConnectionForm(Long id);
    public ResponseEntity<ConnectionFormResponse> createConnectionForm(ConnectionFormRequest connectionFormRequest);
    public ResponseEntity<String> resolveConnectionForm(Long id);
}
