package com.minld._spring_boot.service;

import com.minld._spring_boot.Repository.*;
import com.minld._spring_boot.constant.PredefindRole;
import com.minld._spring_boot.dto.request.CartCreationRequest;
import com.minld._spring_boot.dto.request.SellerCreationRequest;
import com.minld._spring_boot.dto.request.SellerUpdationRequest;
import com.minld._spring_boot.dto.response.AttributesValueResponse;
import com.minld._spring_boot.dto.response.CartResponse;
import com.minld._spring_boot.dto.response.SellerResponse;
import com.minld._spring_boot.entity.*;
import com.minld._spring_boot.exception.AppException;
import com.minld._spring_boot.exception.ErrorCode;
import com.minld._spring_boot.mapper.AttributesMapper;
import com.minld._spring_boot.mapper.CartMapper;
import com.minld._spring_boot.mapper.SellerMapper;
import com.minld._spring_boot.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartService {
    CartRepository cartRepository;
    ProductsRepository productRepository;
    UserRepository userRepository;
    CartMapper cartMapper;
    CartItemRepository cartItemRepository;
    AttributesValueRepository attributesValueRepository;
    ProductOrderRepository productOrderRepository;
    AttributesRepository   attributesRepository;
    AttributesMapper attributesMapper;



    @Transactional
    public CartResponse addItemToCart(CartCreationRequest request) {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (user.getCart() == null) {
            Cart cart = Cart.builder()
                    .user(user)
                    .createdAt(LocalDate.now())
                    .updatedAt(LocalDate.now())
                    .items(new HashSet<>())
                    .build();
            user.setCart(cart);
            cartRepository.save(cart);
        }
        Cart cart = user.getCart();

        Products product = productRepository.findById(request.getId_product())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        if (product.getQuantity() < request.getQuantity()) {
            throw new AppException(ErrorCode.INSUFFICIENT_STOCK);
        }

        if (request.getId_atributes_value() != null && request.getId_atributes_value() <= 0) {
            throw new AppException(ErrorCode.ATTRIBUTE_NOT_FOUND);
        }

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProducts_order().getId_product().equals(request.getId_product())
                        && Objects.equals(item.getProducts_order().getId_atributes_value(), request.getId_atributes_value()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            if (item.getQuantity() + request.getQuantity() > product.getQuantity()) {
                throw new AppException(ErrorCode.INSUFFICIENT_STOCK);
            }
            item.setQuantity(item.getQuantity() + request.getQuantity());
            item.setUnitPrice(product.getPrice() * item.getQuantity());
            cartItemRepository.save(item);
        } else {
            Products_Order products_order = new Products_Order();
            products_order.setId_product(request.getId_product());
            products_order.setName(product.getTitle());
            products_order.setPrice(product.getPrice());
            products_order.setId_user(user.getId());
            products_order.setId_seller(product.getId_Seller());

            if (request.getId_atributes_value() != null) {
//                Set<AttributesValues> attributesValuesProduct = new HashSet<>();

                AttributesValues attributesValues = attributesValueRepository
                        .findById(request.getId_atributes_value())
                        .orElseThrow(() -> new AppException(ErrorCode.ATTRIBUTES_VALUE_NOT_FOUND));
                Attributes attributes = attributesRepository
                        .findById(request.getId_atributes_name())
                        .orElseThrow(() -> new AppException(ErrorCode.ATTRIBUTE_NOT_FOUND));

//                for(AttributesValues attributesValues1 : attributes.getAttributesValues()){
//                    attributesValuesProduct.add(attributesValues1);
//                }
//                products_order.setAttributesValuesProduct(attributesValuesProduct);
                products_order.setAtributes_name(attributes.getName());
                products_order.setId_atributes_name(attributes.getId());
                products_order.setId_atributes_value(request.getId_atributes_value());
                products_order.setAtributes_Value(attributesValues.getName() != null ? attributesValues.getName() : "");
                products_order.setPrice(attributesValues.getPrice()+ product.getPrice());
                Set<MediaFile> images = new HashSet<>();
                if (attributesValues.getImage() != null) {
                    images.add(attributesValues.getImage());
                }
                products_order.setImages(images);
            }
            else {
                // Gán images từ product
                Set<MediaFile> images = new HashSet<>();
                if (product.getImages() != null) {
                    for (MediaFile mediaFile : product.getImages()) {
                            images.add(mediaFile);
                    }
                }
                products_order.setImages(images);
            }
                productOrderRepository.save(products_order);
                CartItem item = new CartItem();
                item.setCart(cart);
                item.setProducts_order(products_order);
                item.setQuantity(request.getQuantity());
                item.setUnitPrice(product.getPrice() * item.getQuantity());
                cart.getItems().add(item);
                cartItemRepository.save(item);

        }
        cart.setUpdatedAt(LocalDate.now());

        return cartMapper.toCartResponse(cartRepository.save(cart));
    }

    @Transactional()
    public CartResponse getCart() {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Cart cart = user.getCart();
        if (cart == null) {
            throw new AppException(ErrorCode.SELLER_NOT_FOUND);
        }
        return cartMapper.toCartResponse(cart);
    }
    public Set<AttributesValueResponse> getAttributes(Long id_atributes) {
      Attributes attributes = attributesRepository.findById(id_atributes).orElseThrow(() -> new AppException(ErrorCode.ATTRIBUTE_NOT_FOUND));
      Set<AttributesValues> attributesValues = attributes.getAttributesValues();
      Set<AttributesValueResponse> attributesValueResponses = new HashSet<>();
      for(AttributesValues attributesValues1 : attributesValues){
          attributesValueResponses.add(attributesMapper.toAttributesValueResponse(attributesValues1));
      }
      return attributesValueResponses;
    }
    public Void UpdateQuantity(Long id, Integer quantity) {
      CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOT_FOUND));
      cartItem.setQuantity(quantity);
     Products products= productRepository.findById(cartItem.getProducts_order().getId_product()).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
    if(products.getQuantity() < quantity){
        throw new AppException(ErrorCode.INSUFFICIENT_STOCK);
    }
      cartItem.setUnitPrice(cartItem.getProducts_order().getPrice() * quantity);
      cartItemRepository.save(cartItem);
      return null;
    }

    @Transactional
    public Void UpdateCartItem(Long id, Long id_atributes_value) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOT_FOUND));
        AttributesValues attributesValues = attributesValueRepository
                .findById(id_atributes_value)
                .orElseThrow(() -> new AppException(ErrorCode.ATTRIBUTES_VALUE_NOT_FOUND));
        Products_Order products_order = cartItem.getProducts_order();
        products_order.setAtributes_Value(attributesValues.getName());
        products_order.setId_atributes_value(attributesValues.getId());


        if (attributesValues.getImage() != null) {
            Set<MediaFile> images = new HashSet<>();

            images.add(attributesValues.getImage());
            products_order.setImages(images);
        }

        products_order.setPrice(attributesValues.getPrice() + products_order.getPrice());
        productOrderRepository.save(products_order);
        cartItem.setProducts_order(products_order);
        cartItemRepository.save(cartItem);
        return null;
    }


    public void deleteCartItem(Long id) {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
     Cart cart = user.getCart();
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOT_FOUND));

        if (!cartItem.getCart().getId().equals(cart.getId())) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }
        cartItemRepository.delete(cartItem);
    }

}
