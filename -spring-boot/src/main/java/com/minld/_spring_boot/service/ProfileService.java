package com.minld._spring_boot.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.minld._spring_boot.Repository.MediaReponsitory;
import com.minld._spring_boot.Repository.ProfileReponsitory;
import com.minld._spring_boot.Repository.UserRepository;
import com.minld._spring_boot.dto.request.ProfileUpdationRequest;
import com.minld._spring_boot.dto.response.ProfileResonse;
import com.minld._spring_boot.entity.ProfileUser;
import com.minld._spring_boot.exception.AppException;
import com.minld._spring_boot.exception.ErrorCode;
import com.minld._spring_boot.mapper.ProfileMapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileService {

    UserRepository userRepository;

    ProfileReponsitory profileRepository;

    ProfileMapper profileMapper;

    CloudinaryService cloudinaryService;

    MediaReponsitory mediaReponsitory;

    public ProfileResonse update(Long id, ProfileUpdationRequest request) throws IOException {
        ProfileUser profileUser =
                profileRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PROFILE_NOT_FOUND));
        profileMapper.updateProfile(profileUser, request);
        return profileMapper.toProfileResponse(profileRepository.save(profileUser));
    }
}
