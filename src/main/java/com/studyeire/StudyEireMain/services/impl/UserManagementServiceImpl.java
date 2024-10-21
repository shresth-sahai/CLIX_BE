package com.studyeire.StudyEireMain.services.impl;

import com.studyeire.StudyEireMain.config.JwtService;
import com.studyeire.StudyEireMain.constants.UserRole;
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
import com.studyeire.StudyEireMain.model.user.AppUser;
import com.studyeire.StudyEireMain.respository.AppUserRepository;
import com.studyeire.StudyEireMain.services.UserManagementService;
import com.studyeire.StudyEireMain.utils.CommonHelperMethods;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.studyeire.StudyEireMain.constants.ConstantStrings.*;

@Service
@RequiredArgsConstructor
public class UserManagementServiceImpl implements UserDetailsService, UserManagementService {

    private final AppUserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

    @Override
    public UserRegisterResponse registerUser(UserRegisterRequest request) throws InvalidUserRegistrationRequest {
            validateRegisterRequest(request);
            AppUser user = AppUser.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .userRoles(new HashSet<>(List.of(UserRole.UNVERIFIED_USER)))
                    .locked(false)
                    .expired(false)
                    .credentialsExpired(false)
                    .enabled(true)
                    .build();
            userRepository.save(user);
            String jwtToken = jwtService.generateToken(user);
            return  UserRegisterResponse.builder()
                    .username(request.getUsername())
                    .token(jwtToken)
                    .message(USER_REGISTER_SUCCESS)
                    .build();
    }

    @Override
    public UserSigninResponse signinUser(UserSigninRequest request) throws InvalidUserSigninRequest {
            validateSigninRequest(request);
            Optional<AppUser> optionalUser;

            if(!CommonHelperMethods.isNullOrEmpty(request.getEmail()))
                optionalUser = userRepository.findByEmail(request.getEmail());
            else
                optionalUser = userRepository.findByUsername(request.getUsername());

            if(optionalUser.isEmpty()) {
                return null;
            }

            if(CommonHelperMethods.isNullOrEmpty(request.getUsername())) {
                request.setUsername(optionalUser.get().getUsername());
            }
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            AppUser user = optionalUser.get();
            String jwtToken = jwtService.generateToken(user);
            return UserSigninResponse.builder()
                    .username(user.getUsername())
                    .token(jwtToken)
                    .build();

    }

    @Override
    public UserResponse getUser(String username) throws UsernameNotFoundException {
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException(USER_NOT_FOUND);
        }
        AppUser user = optionalUser.get();
        return UserResponse.builder()
                .username(user.getUsername())
                .id(user.getId())
                .dob(user.getDob())
                .accountType(user.getAccountType())
                .addresses(user.getAddresses())
                .children(user.getChildren().stream().map((child -> child.getUsername())).collect(Collectors.toSet()))
                .currentInstitute(Pair.of(user.getCurrentInstitute().getId(), user.getCurrentInstitute().getInstitutionName()))
                .roles(user.getUserRoles())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .parents(user.getParents().stream().map(parent -> parent.getUsername()).collect(Collectors.toSet()))
                .children(user.getChildren().stream().map(child -> child.getUsername()).collect(Collectors.toSet()))
                .mobileNumber(user.getMobileNumber())
                .credentialsExpired(user.isCredentialsExpired())
                .enabled(user.isEnabled())
                .expired(user.isExpired())
                .locked(user.isLocked())
                .message(USER_FOUND_SUCCESS)
                .build();
    }

    @Override
    public UserResponse updateUser(UserUpdateRequest request) throws InvalidUserUpdateRequest {
        return null;
    }

    @Override
    public UserDeleteResponse deleteUser(String username) throws UsernameNotFoundException {
            Optional<AppUser> optionalAppUser = userRepository.findByUsername(username);
            if(optionalAppUser.isEmpty()) {
                throw new UsernameNotFoundException(USER_NOT_FOUND);
            }
            AppUser user = optionalAppUser.get();
            user.setEnabled(false);
            userRepository.save(user);
            return UserDeleteResponse.builder()
                    .username(user.getUsername())
                    .message(USER_DELETE_SUCCESS)
                    .isDeleted(true)
                    .build();
    }

    private void validateRegisterRequest(UserRegisterRequest request) throws InvalidUserRegistrationRequest {
        if(CommonHelperMethods.isNullOrEmpty(request.getEmail()))
            throw new InvalidUserRegistrationRequest("Invalid registration email.");

        if(CommonHelperMethods.isNullOrEmpty(request.getUsername()))
            throw new InvalidUserRegistrationRequest("Invalid registration username.");

        if(CommonHelperMethods.isNullOrEmpty(request.getPassword()))
            throw new InvalidUserRegistrationRequest("Invalid registration password.");

        Optional<AppUser> optionalUser = userRepository.findByEmail(request.getEmail());
        if(!optionalUser.isEmpty()) {
            throw new InvalidUserRegistrationRequest("User with email address " + request.getEmail() + " already exist.");
        }
        optionalUser = userRepository.findByUsername(request.getUsername());
        if(!optionalUser.isEmpty()) {
            throw new InvalidUserRegistrationRequest("User with username " + request.getUsername() + " already exist.");
        }
    }

    private void validateSigninRequest(UserSigninRequest request) throws InvalidUserSigninRequest {
        if(CommonHelperMethods.isNullOrEmpty(request.getUsername()) && CommonHelperMethods.isNullOrEmpty(request.getEmail()))
            throw new InvalidUserSigninRequest("No username and email provided.");
        if(CommonHelperMethods.isNullOrEmpty(request.getPassword()))
            throw new InvalidUserSigninRequest("No password provided.");
    }
}
