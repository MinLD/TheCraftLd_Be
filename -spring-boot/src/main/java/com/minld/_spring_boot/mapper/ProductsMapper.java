package com.minld._spring_boot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.minld._spring_boot.dto.request.ProductsCreationRequest;
import com.minld._spring_boot.dto.request.ProductsUpdateRequest;
import com.minld._spring_boot.dto.response.ProductsResponse;
import com.minld._spring_boot.entity.Products;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductsMapper {

    Products toProducts(ProductsCreationRequest request);

    ProductsResponse toProductsResponse(Products products);

    void updateProducts(@MappingTarget Products products, ProductsUpdateRequest request);
}
