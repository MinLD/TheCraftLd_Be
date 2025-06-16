package com.minld._spring_boot.mapper;

import com.minld._spring_boot.dto.request.CartCreationRequest;
import com.minld._spring_boot.dto.response.CartItemResponse;
import com.minld._spring_boot.dto.response.CartResponse;
import com.minld._spring_boot.entity.Cart;
import com.minld._spring_boot.entity.CartItem;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Override
    public Cart toCart(CartCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Cart.CartBuilder cart = Cart.builder();

        return cart.build();
    }

    @Override
    public CartResponse toCartResponse(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartResponse.CartResponseBuilder cartResponse = CartResponse.builder();

        cartResponse.id( cart.getId() );
        cartResponse.items( cartItemSetToCartItemResponseSet( cart.getItems() ) );
        cartResponse.createdAt( cart.getCreatedAt() );
        cartResponse.updatedAt( cart.getUpdatedAt() );

        return cartResponse.build();
    }

    protected CartItemResponse cartItemToCartItemResponse(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }

        CartItemResponse.CartItemResponseBuilder cartItemResponse = CartItemResponse.builder();

        cartItemResponse.id( cartItem.getId() );
        cartItemResponse.products_order( cartItem.getProducts_order() );
        cartItemResponse.quantity( cartItem.getQuantity() );
        cartItemResponse.unitPrice( cartItem.getUnitPrice() );

        return cartItemResponse.build();
    }

    protected Set<CartItemResponse> cartItemSetToCartItemResponseSet(Set<CartItem> set) {
        if ( set == null ) {
            return null;
        }

        Set<CartItemResponse> set1 = new LinkedHashSet<CartItemResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CartItem cartItem : set ) {
            set1.add( cartItemToCartItemResponse( cartItem ) );
        }

        return set1;
    }
}
