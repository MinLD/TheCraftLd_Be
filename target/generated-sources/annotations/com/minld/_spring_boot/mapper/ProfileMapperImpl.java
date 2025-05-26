package com.minld._spring_boot.mapper;

import com.minld._spring_boot.dto.request.ProfileCreationRequest;
import com.minld._spring_boot.dto.request.ProfileUpdationRequest;
import com.minld._spring_boot.dto.response.ProfileResonse;
import com.minld._spring_boot.entity.ProfileUser;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class ProfileMapperImpl implements ProfileMapper {

    @Override
    public ProfileUser toProfile(ProfileCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        ProfileUser.ProfileUserBuilder profileUser = ProfileUser.builder();

        profileUser.fullName( request.getFullName() );
        profileUser.email( request.getEmail() );

        return profileUser.build();
    }

    @Override
    public ProfileResonse toProfileResponse(ProfileUser profileUser) {
        if ( profileUser == null ) {
            return null;
        }

        ProfileResonse.ProfileResonseBuilder profileResonse = ProfileResonse.builder();

        profileResonse.id( profileUser.getId() );
        profileResonse.fullName( profileUser.getFullName() );
        profileResonse.email( profileUser.getEmail() );
        profileResonse.address( profileUser.getAddress() );
        profileResonse.city( profileUser.getCity() );
        profileResonse.country( profileUser.getCountry() );
        profileResonse.phone( profileUser.getPhone() );
        profileResonse.dob( profileUser.getDob() );
        profileResonse.gender( profileUser.getGender() );

        return profileResonse.build();
    }

    @Override
    public void updateProfile(ProfileUser profileUser, ProfileUpdationRequest request) {
        if ( request == null ) {
            return;
        }

        if ( request.getFullName() != null ) {
            profileUser.setFullName( request.getFullName() );
        }
        if ( request.getAddress() != null ) {
            profileUser.setAddress( request.getAddress() );
        }
        if ( request.getCity() != null ) {
            profileUser.setCity( request.getCity() );
        }
        if ( request.getCountry() != null ) {
            profileUser.setCountry( request.getCountry() );
        }
        if ( request.getPhone() != null ) {
            profileUser.setPhone( request.getPhone() );
        }
        if ( request.getDob() != null ) {
            profileUser.setDob( request.getDob() );
        }
        if ( request.getGender() != null ) {
            profileUser.setGender( request.getGender() );
        }
    }
}
