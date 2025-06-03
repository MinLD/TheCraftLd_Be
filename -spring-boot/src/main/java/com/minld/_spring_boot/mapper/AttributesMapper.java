package com.minld._spring_boot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.minld._spring_boot.dto.request.AttributesCreationRequest;
import com.minld._spring_boot.dto.response.AttributesResponse;
import com.minld._spring_boot.dto.response.AttributesValueResponse;
import com.minld._spring_boot.entity.Attributes;
import com.minld._spring_boot.entity.AttributesValues;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AttributesMapper {

    Attributes toAttributes(AttributesCreationRequest request);

    AttributesResponse toAttributesResponse(Attributes attributes);

    AttributesValueResponse toAttributesValueResponse(AttributesValues attributesValues);

    //    void updateCategories(@MappingTarget Categories categories, CategoriesUpdationRequest request);
}
