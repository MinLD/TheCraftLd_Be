package com.minld._spring_boot.mapper;

import com.minld._spring_boot.dto.request.CartCreationRequest;
import com.minld._spring_boot.dto.request.ProfileCreationRequest;
import com.minld._spring_boot.dto.request.ProfileUpdationRequest;
import com.minld._spring_boot.dto.response.CartResponse;
import com.minld._spring_boot.dto.response.ProfileResonse;
import com.minld._spring_boot.entity.Cart;
import com.minld._spring_boot.entity.ProfileUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CartMapper {
    Cart toCart(CartCreationRequest request);

    CartResponse toCartResponse(Cart cart);

}
