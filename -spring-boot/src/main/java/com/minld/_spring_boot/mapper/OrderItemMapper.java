package com.minld._spring_boot.mapper;

import com.minld._spring_boot.dto.request.CartCreationRequest;
import com.minld._spring_boot.dto.response.CartResponse;
import com.minld._spring_boot.dto.response.OrderItemResponse;
import com.minld._spring_boot.entity.Cart;
import com.minld._spring_boot.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderItemMapper {

    OrderItemResponse toOrderItemResponse(OrderItem orderItem);

}
