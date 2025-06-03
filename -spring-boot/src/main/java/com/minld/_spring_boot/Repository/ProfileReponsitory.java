package com.minld._spring_boot.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minld._spring_boot.entity.ProfileUser;
import com.minld._spring_boot.entity.User;

@Repository
public interface ProfileReponsitory extends JpaRepository<ProfileUser, Long> {
    Optional<ProfileUser> findProfileByUser(User user);
}
