package com.studyeire.StudyEireMain.respository;

import com.studyeire.StudyEireMain.constants.ConnectionFormStatus;
import com.studyeire.StudyEireMain.model.ConnectionForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionFormRepository extends JpaRepository<ConnectionForm, Long> {
    public List<ConnectionForm> findByStatus(ConnectionFormStatus status);
}
