package com.studyeire.StudyEireMain.services.impl;

import com.studyeire.StudyEireMain.constants.ConnectionFormStatus;
import com.studyeire.StudyEireMain.constants.ConstantStrings;
import com.studyeire.StudyEireMain.exceptions.connectionformexceptions.InvalidConnectionFormRequest;
import com.studyeire.StudyEireMain.model.ConnectionForm;
import com.studyeire.StudyEireMain.model.request.ConnectionFormRequest;
import com.studyeire.StudyEireMain.model.response.ConnectionFormResponse;
import com.studyeire.StudyEireMain.respository.ConnectionFormRepository;
import com.studyeire.StudyEireMain.services.ConnectionFormService;
import com.studyeire.StudyEireMain.utils.CommonHelperMethods;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConnectionFormServiceImpl implements ConnectionFormService {

    private final ConnectionFormRepository connectionFormRepository;

    @Override
    public ResponseEntity<List<ConnectionFormResponse> > getAllConnectionForms(MultiValueMap <String, String> filter) {
        try {
            List<ConnectionForm> connectionForms;
            if(filter.containsKey("status")){
                ConnectionFormStatus status = ConnectionFormStatus.getEnumValue(filter.getFirst("status"));
                connectionForms = connectionFormRepository.findByStatus(status);
            }
            else {
                connectionForms = connectionFormRepository.findAll();
            }
            return new ResponseEntity<>(connectionForms.stream()
                    .map(connectionForm -> convertConnectionFormToConnectionFormResponse(connectionForm))
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ConnectionFormResponse> getConnectionForm(Long id) {
        try{
            Optional<ConnectionForm> optionalConnectionForm = connectionFormRepository.findById(id);
            if(optionalConnectionForm.isEmpty())
                throw new NoSuchElementException();
            return new ResponseEntity<>(convertConnectionFormToConnectionFormResponse(optionalConnectionForm.get()),
                    HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ConnectionFormResponse> createConnectionForm(ConnectionFormRequest connectionFormRequest) {
        try {
            validateConnectionFormRequest(connectionFormRequest);
            ConnectionForm savedForm = connectionFormRepository.save(convertFormRequestToConnectionForm(connectionFormRequest));
            return new ResponseEntity<>(
                        convertConnectionFormToConnectionFormResponse(savedForm),
                        HttpStatus.OK);
        }
        catch (InvalidConnectionFormRequest e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<String> resolveConnectionForm(Long id) {
        try{
            Optional<ConnectionForm> optionalConnectionForm = connectionFormRepository.findById(id);
            if(optionalConnectionForm.isEmpty())
                throw new NoSuchElementException();
            ConnectionForm connectionForm = optionalConnectionForm.get();
            connectionForm.setStatus(ConnectionFormStatus.RESOLVED);
            connectionFormRepository.save(connectionForm);
            return new ResponseEntity<>( ConstantStrings.CONNECTION_FORM_RESOLVED_OK + id, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(ConstantStrings.CONNECTION_FORM_NOT_FOUND + id, HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(ConstantStrings.CONNECTION_FORM_INTERNAL_ERROR + id + ".", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Helper methods for service
    private ConnectionForm convertFormRequestToConnectionForm(ConnectionFormRequest connectionFormRequest) {
        return ConnectionForm.builder()
                .name(connectionFormRequest.getName())
                .email(connectionFormRequest.getEmail())
                .description(connectionFormRequest.getDescription())
                .status(ConnectionFormStatus.ACTIVE)
                .createdOn(new Date())
                .build();
    }
    private ConnectionFormResponse convertConnectionFormToConnectionFormResponse(ConnectionForm connectionForm) {
        return ConnectionFormResponse.builder()
                .id(connectionForm.getId())
                .name(connectionForm.getName())
                .email(connectionForm.getEmail())
                .description(connectionForm.getDescription())
                .status(connectionForm.getStatus())
                .createdOn(connectionForm.getCreatedOn())
                .build();
    }
    private void validateConnectionFormRequest(ConnectionFormRequest connectionFormRequest) throws InvalidConnectionFormRequest {
        if(connectionFormRequest == null)
            throw new InvalidConnectionFormRequest(ConstantStrings.CONNECTION_FORM_IS_INVALID);

        if(CommonHelperMethods.isNullOrEmpty(connectionFormRequest.getName()))
            throw new InvalidConnectionFormRequest(ConstantStrings.CONNECTION_FORM_NAME_INVALID);

        if(!Pattern.compile(ConstantStrings.EMAIL_REGEX).matcher(connectionFormRequest.getEmail()).matches())
            throw new InvalidConnectionFormRequest(ConstantStrings.CONNECTION_FORM_EMAIL_INVALID);

        if(CommonHelperMethods.isNullOrEmpty(connectionFormRequest.getDescription()))
            throw new InvalidConnectionFormRequest(ConstantStrings.CONNECTION_FORM_DESCRIPTION_INVALID);
    }
}
