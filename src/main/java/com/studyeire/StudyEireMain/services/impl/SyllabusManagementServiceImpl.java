package com.studyeire.StudyEireMain.services.impl;

import com.studyeire.StudyEireMain.exceptions.syllabusexceptions.SyllabusNotFound;
import com.studyeire.StudyEireMain.model.academics.Syllabus;
import com.studyeire.StudyEireMain.model.response.syllabusresponse.GetSyllabusResponse;
import com.studyeire.StudyEireMain.respository.SyllabusRepository;
import com.studyeire.StudyEireMain.services.SyllabusManagementService;
import com.studyeire.StudyEireMain.utils.CommonHelperMethods;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SyllabusManagementServiceImpl implements SyllabusManagementService {

    private final SyllabusRepository syllabusRepository;

    @Override
    public GetSyllabusResponse getSyllabus(Long syllabusId) throws SyllabusNotFound {
        Optional<Syllabus> optionalSyllabus = syllabusRepository.findById(syllabusId);
        if(optionalSyllabus.isEmpty()) {
            throw new SyllabusNotFound("Syllabus not found, syllabusId: " + syllabusId);
        }
        Syllabus syllabus = optionalSyllabus.get();
        return CommonHelperMethods.convertSyllabusToSyllabusResponse(syllabus, "Successfully retrieved syllabus with id " + syllabus.getSyllabusId());
    }
}
