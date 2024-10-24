package com.studyeire.StudyEireMain.respository;

import com.studyeire.StudyEireMain.model.academics.Syllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SyllabusRepository extends JpaRepository<Syllabus, Long> {
}
