package com.studyeire.StudyEireMain.config;

import com.studyeire.StudyEireMain.respository.SyllabusRepository;
import com.studyeire.StudyEireMain.respository.AppUserRepository;
import com.studyeire.StudyEireMain.respository.ConnectionFormRepository;
import com.studyeire.StudyEireMain.respository.RequestTeacherRepository;
import com.studyeire.StudyEireMain.services.*;
import com.studyeire.StudyEireMain.services.impl.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    
    private final AppUserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtService jwtService() {
        return new JwtService();
    }

    @Bean
    public ConnectionFormService connectionFormService(ConnectionFormRepository connectionFormRepository) {
        return new ConnectionFormServiceImpl(connectionFormRepository);
    }

    @Bean
    public StudentManagementService studentManagementService(AppUserRepository appUserRepository,
                                                             RequestTeacherRepository requestTeacherRepository,
                                                             SyllabusRepository academicRepository) {
        return new StudentManagementServiceImpl(appUserRepository, requestTeacherRepository, academicRepository);
    }

    @Bean
    public TeacherManagementService teacherManagementService() { return new TeacherManagementServiceImpl(); }

    @Bean
    public ParentManagementService parentManagementService() { return new ParentManagementServiceImpl(); }

    @Bean
    public AdminManagementService adminManagementService() { return new AdminManagementServiceImpl(); }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
