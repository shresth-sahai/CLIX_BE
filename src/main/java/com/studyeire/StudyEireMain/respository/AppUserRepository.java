package com.studyeire.StudyEireMain.respository;

import com.studyeire.StudyEireMain.model.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    public Optional<AppUser> findByUsername(String username);

    public Optional<AppUser> findByEmail(String email);
}
