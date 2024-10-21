package com.studyeire.StudyEireMain.services.impl;

import com.studyeire.StudyEireMain.exceptions.studentmanagementexceptions.InvalidSyllabusUploadRequest;
import com.studyeire.StudyEireMain.model.RequestTeacher;
import com.studyeire.StudyEireMain.model.academics.Subject;
import com.studyeire.StudyEireMain.model.academics.Syllabus;
import com.studyeire.StudyEireMain.model.request.BookTeacherRequest;
import com.studyeire.StudyEireMain.model.request.UploadSyllabusRequest;
import com.studyeire.StudyEireMain.model.response.*;
import com.studyeire.StudyEireMain.model.response.syllabusresponse.GetAllSyllabusResponse;
import com.studyeire.StudyEireMain.model.response.syllabusresponse.GetSyllabusResponse;
import com.studyeire.StudyEireMain.model.response.syllabusresponse.SubjectResponseForSyllabus;
import com.studyeire.StudyEireMain.model.response.syllabusresponse.SyllabusUploadResponse;
import com.studyeire.StudyEireMain.model.user.AppUser;
import com.studyeire.StudyEireMain.respository.SyllabusRepository;
import com.studyeire.StudyEireMain.respository.AppUserRepository;
import com.studyeire.StudyEireMain.respository.RequestTeacherRepository;
import com.studyeire.StudyEireMain.services.StudentManagementService;
import com.studyeire.StudyEireMain.utils.CommonHelperMethods;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentManagementServiceImpl implements StudentManagementService {

     private final AppUserRepository userRepository;
     private final RequestTeacherRepository requestTeacherRepository;
     private final SyllabusRepository syllabusRepository;

     @PersistenceContext
     private EntityManager entityManager;

    @Override
    public BookTeacherResponse bookTeacher(String username, BookTeacherRequest request) throws Exception {
        try {
            AppUser user = getUserByUsername(username);
            String parentUsername = null;

            if (!CommonHelperMethods.isNullOrEmpty(request.getParentUsername())) {
                Optional<AppUser> optionalParent = userRepository.findByUsername(request.getParentUsername());
                if (optionalParent.isPresent()) {
                    parentUsername = optionalParent.get().getUsername();
                } else {
                    throw new UsernameNotFoundException("Parent username not found. Please remove or provide a valid Parent username.");
                }
            }

            RequestTeacher requestTeacher = RequestTeacher.builder()
                    .studentUsername(user.getUsername())
                    .contactEmail(CommonHelperMethods.isNullOrEmpty(request.getContactEmail()) ? user.getEmail() : request.getContactEmail())
                    .contactPhoneNumber(CommonHelperMethods.isNullOrEmpty(request.getContactPhoneNumber()) ? user.getMobileNumber() : request.getContactPhoneNumber())
                    .subjectDescription(request.getSubjectDescription())
                    .meetingTimeDescription(request.getMeetingTimeDescription())
                    .parentUsername(parentUsername)
                    .build();
            requestTeacherRepository.save(requestTeacher);
            return BookTeacherResponse.builder()
                    .message("Successfully requested teacher")
                    .build();
        }
        catch (Exception ex) {
            throw new Exception("Error creating request for teacher.");
        }
    }

    @Override
    public SyllabusUploadResponse uploadSyllabus(String username, UploadSyllabusRequest request) throws InvalidSyllabusUploadRequest {
        AppUser user = getUserByUsername(username);
        Syllabus syllabus = Syllabus.builder()
                .syllabusName(request.getSyllabusName())
                .type(request.getType())
                .createdBy(user)
                .subjects(request.getSubjects().parallelStream().map(subjectId -> entityManager.getReference(Subject.class, subjectId)).collect(Collectors.toSet()))
                .description(request.getDescription())
                .build();
        syllabus = syllabusRepository.save(syllabus);
        return SyllabusUploadResponse.builder()
                .syllabusId(syllabus.getSyllabusId())
                .syllabusType(syllabus.getType())
                .createdByUser(user.getUsername())
                .syllabusName(syllabus.getSyllabusName())
                .syllabusDescription(syllabus.getDescription())
                .message("Successfully created the syllabus.")
                .build();
    }

    @Override
    public GetAllSyllabusResponse getAllSyllabus(String username) throws UsernameNotFoundException {
        AppUser user = getUserByUsername(username);
        Set<Syllabus> syllabi = user.getSyllabi();
        return GetAllSyllabusResponse.builder().syllabi(syllabi.parallelStream().map(syllabus -> CommonHelperMethods.convertSyllabusToSyllabusResponse(syllabus, ""))
                .collect(Collectors.toSet()))
                .message("Successfully retrieved all syllabus for the user.")
                .build();
    }

    @Override
    public StudentProgressResponse getStudentProgress(String username) throws UsernameNotFoundException {
        return null;
    }

    private AppUser getUserByUsername(String username) throws UsernameNotFoundException {
        if (CommonHelperMethods.isNullOrEmpty(username)) {
            throw new UsernameNotFoundException("Student username can't be empty.");
        }
        Optional<AppUser> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found.");
        }
        return userOptional.get();
    }
}
