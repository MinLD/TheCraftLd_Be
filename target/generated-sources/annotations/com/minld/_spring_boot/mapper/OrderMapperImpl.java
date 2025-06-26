package com.minld._spring_boot.mapper;

import com.minld._spring_boot.dto.response.AddressResponse;
import com.minld._spring_boot.dto.response.OrderResponse;
import com.minld._spring_boot.entity.Address;
import com.minld._spring_boot.entity.Order;
import com.minld._spring_boot.entity.OrderItem;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderResponse toOrderResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponse.OrderResponseBuilder orderResponse = OrderResponse.builder();

        orderResponse.orderId( order.getOrderId() );
        orderResponse.totalPrice( order.getTotalPrice() );
        orderResponse.status( order.getStatus() );
        orderResponse.shippingAddress( addressToAddressResponse( order.getShippingAddress() ) );
        orderResponse.paymentMethod( order.getPaymentMethod() );
        Set<OrderItem> set = order.getItems();
        if ( set != null ) {
            orderResponse.items( new LinkedHashSet<OrderItem>( set ) );
        }
        orderResponse.createdAt( order.getCreatedAt() );
        orderResponse.updatedAt( order.getUpdatedAt() );

        return orderResponse.build();
    }

    protected AddressResponse addressToAddressResponse(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressResponse.AddressResponseBuilder addressResponse = AddressResponse.builder();

        addressResponse.id( address.getId() );
        addressResponse.name( address.getName() );
        addressResponse.phone( address.getPhone() );
        addressResponse.address( address.getAddress() );
        addressResponse.detailsAddress( address.getDetailsAddress() );
        addressResponse.isType( address.getIsType() );

        return addressResponse.build();
    }
}
