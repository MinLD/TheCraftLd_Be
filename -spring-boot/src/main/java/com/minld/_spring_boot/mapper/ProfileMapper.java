package com.minld._spring_boot.mapper;

import org.mapstruct.*;

import com.minld._spring_boot.dto.request.ProfileCreationRequest;
import com.minld._spring_boot.dto.request.ProfileUpdationRequest;
import com.minld._spring_boot.dto.response.ProfileResonse;
import com.minld._spring_boot.entity.ProfileUser;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProfileMapper {
    // bỏ qua các thuộc tính có giá trị null trong đối tượng nguồn (source). Nghĩa là,
    // nếu một thuộc tính trong đối tượng nguồn là null, thuộc tính tương ứng trong
    // đối tượng đích (destination) sẽ giữ nguyên giá trị hiện tại (nếu có) thay vì
    // bị ghi đè thành null.

    ProfileUser toProfile(ProfileCreationRequest request);

    ProfileResonse toProfileResponse(ProfileUser profileUser);

    void updateProfile(@MappingTarget ProfileUser profileUser, ProfileUpdationRequest request);
}
