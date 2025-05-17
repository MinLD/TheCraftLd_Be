package com.minld._spring_boot.mapper;

import com.minld._spring_boot.dto.request.ProfileUpdationRequest;
import com.minld._spring_boot.dto.request.SellerUpdationRequest;
import com.minld._spring_boot.dto.response.SellerResponse;
import com.minld._spring_boot.entity.ProfileUser;
import com.minld._spring_boot.entity.Seller;
import org.mapstruct.Mapper;

import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SellerMapper {

    Seller toSeller(SellerUpdationRequest request);

    SellerResponse toSellerResponse(Seller seller);

    void updateSeller(@MappingTarget Seller seller, SellerUpdationRequest request);
}
