package com.minld._spring_boot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.minld._spring_boot.dto.request.MediaUpdateRequest;
import com.minld._spring_boot.dto.response.MediaResponse;
import com.minld._spring_boot.entity.MediaFile;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MediaMapper {

    MediaFile toMediaFile(MediaUpdateRequest request);

    MediaResponse toMediaResponse(MediaFile mediaFile);

    void updateMedia(@MappingTarget MediaFile mediaFile, MediaUpdateRequest request);
}
