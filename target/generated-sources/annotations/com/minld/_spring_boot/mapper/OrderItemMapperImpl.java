package com.minld._spring_boot.mapper;

import com.minld._spring_boot.dto.response.OrderItemResponse;
import com.minld._spring_boot.entity.OrderItem;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemResponse.OrderItemResponseBuilder orderItemResponse = OrderItemResponse.builder();

        orderItemResponse.id( orderItem.getId() );
        orderItemResponse.products_order( orderItem.getProducts_order() );
        orderItemResponse.quantity( orderItem.getQuantity() );
        orderItemResponse.unitPrice( orderItem.getUnitPrice() );

        return orderItemResponse.build();
    }
}
