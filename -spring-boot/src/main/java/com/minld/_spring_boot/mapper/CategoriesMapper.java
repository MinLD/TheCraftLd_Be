package com.minld._spring_boot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.minld._spring_boot.dto.request.CategoriesCreationRequest;
import com.minld._spring_boot.dto.request.CategoriesUpdationRequest;
import com.minld._spring_boot.dto.response.CategoriesResponse;
import com.minld._spring_boot.entity.Categories;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoriesMapper {

    Categories toCategories(CategoriesCreationRequest request);

    CategoriesResponse toCategoriesResponse(Categories categories);

    void updateCategories(@MappingTarget Categories categories, CategoriesUpdationRequest request);
}
