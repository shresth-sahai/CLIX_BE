package com.studyeire.StudyEireMain.controllers;

import com.studyeire.StudyEireMain.exceptions.authexceptions.InvalidUserRegistrationRequest;
import com.studyeire.StudyEireMain.exceptions.authexceptions.InvalidUserSigninRequest;
import com.studyeire.StudyEireMain.exceptions.authexceptions.InvalidUserUpdateRequest;
import com.studyeire.StudyEireMain.model.request.UserRegisterRequest;
import com.studyeire.StudyEireMain.model.request.UserSigninRequest;
import com.studyeire.StudyEireMain.model.request.UserUpdateRequest;
import com.studyeire.StudyEireMain.model.response.UserDeleteResponse;
import com.studyeire.StudyEireMain.model.response.UserRegisterResponse;
import com.studyeire.StudyEireMain.model.response.UserResponse;
import com.studyeire.StudyEireMain.model.response.UserSigninResponse;
import com.studyeire.StudyEireMain.services.UserManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import static com.studyeire.StudyEireMain.constants.ConstantStrings.*;

@RestController
@RequestMapping(API_V1_AUTH)
@RequiredArgsConstructor
public class UserController {

    private final UserManagementService userManagementService;

    @PostMapping("/auth/reg")
    public ResponseEntity<UserRegisterResponse> registerUser(@RequestBody UserRegisterRequest request) {
        System.out.println("Received registration request: " + request);
        try {
            UserRegisterResponse userRegisterResponse = userManagementService.registerUser(request);
            return new ResponseEntity<>(userRegisterResponse, HttpStatus.OK);
        } catch (InvalidUserRegistrationRequest ex) {
            return new ResponseEntity<>(UserRegisterResponse.builder().message(ex.getMessage()).build(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(UserRegisterResponse.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/auth")
    public ResponseEntity<UserSigninResponse> signinUser(@RequestBody UserSigninRequest request) {
        try{
            UserSigninResponse userSigninResponse = userManagementService.signinUser(request);
            if(userSigninResponse == null) {
                return new ResponseEntity<>(UserSigninResponse.builder()
                        .message(USER_NOT_FOUND)
                        .build(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(userSigninResponse, HttpStatus.OK);
        }
        catch (InvalidUserSigninRequest ex) {
            return new ResponseEntity<>(UserSigninResponse.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(UserSigninResponse.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String username) {
        try{
            UserResponse userResponse = userManagementService.getUser(username);
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
        catch (UsernameNotFoundException ex){
            return new ResponseEntity<>(UserResponse.builder()
                    .message(ex.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        }
        catch(Exception e){
            return new ResponseEntity<>(UserResponse.builder()
                    .message(e.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
