package com.minld._spring_boot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.minld._spring_boot.dto.request.SellerUpdationRequest;
import com.minld._spring_boot.dto.response.SellerResponse;
import com.minld._spring_boot.entity.Seller;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SellerMapper {

    Seller toSeller(SellerUpdationRequest request);

    SellerResponse toSellerResponse(Seller seller);

    void updateSeller(@MappingTarget Seller seller, SellerUpdationRequest request);
}
