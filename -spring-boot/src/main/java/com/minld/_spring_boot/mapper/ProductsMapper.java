package com.minld._spring_boot.mapper;

import com.minld._spring_boot.dto.request.CategoriesCreationRequest;
import com.minld._spring_boot.dto.request.CategoriesUpdationRequest;
import com.minld._spring_boot.dto.request.ProductsCreationRequest;
import com.minld._spring_boot.dto.response.CategoriesResponse;
import com.minld._spring_boot.dto.response.ProductsResponse;
import com.minld._spring_boot.entity.Categories;
import com.minld._spring_boot.entity.Products;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductsMapper {

    Products toProducts(ProductsCreationRequest request);

    ProductsResponse toProductsResponse(Products products);

//    void updateCategories(@MappingTarget Categories categories, CategoriesUpdationRequest request);
}
