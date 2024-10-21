package com.studyeire.StudyEireMain.respository;

import com.studyeire.StudyEireMain.model.RequestTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestTeacherRepository extends JpaRepository<RequestTeacher, Long> {
}
