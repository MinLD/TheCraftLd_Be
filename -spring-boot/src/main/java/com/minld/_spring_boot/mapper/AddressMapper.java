package com.minld._spring_boot.mapper;

import com.minld._spring_boot.dto.request.AddressCreationRequest;
import com.minld._spring_boot.dto.request.CategoriesCreationRequest;
import com.minld._spring_boot.dto.request.CategoriesUpdationRequest;
import com.minld._spring_boot.dto.response.AddressResponse;
import com.minld._spring_boot.dto.response.CategoriesResponse;
import com.minld._spring_boot.entity.Address;
import com.minld._spring_boot.entity.Categories;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper {

    Address toAddress(AddressCreationRequest request);

    AddressResponse toAddressResponse(Address address);



    void updateAddress(@MappingTarget Address address, AddressCreationRequest request);
}
