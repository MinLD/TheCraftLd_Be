package com.minld._spring_boot.service;

import com.minld._spring_boot.Repository.*;
import com.minld._spring_boot.dto.request.AddressCreationRequest;
import com.minld._spring_boot.dto.request.OrderCreationRequest;
import com.minld._spring_boot.dto.request.OrderItemCreationRequest;
import com.minld._spring_boot.dto.response.AddressResponse;
import com.minld._spring_boot.dto.response.OrderItemResponse;
import com.minld._spring_boot.dto.response.OrderResponse;
import com.minld._spring_boot.entity.*;
import com.minld._spring_boot.exception.AppException;
import com.minld._spring_boot.exception.ErrorCode;
import com.minld._spring_boot.mapper.AddressMapper;
import com.minld._spring_boot.mapper.OrderMapper;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;



    @Slf4j
    @Service
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public class OrderService {

        UserRepository userRepository;
        ProductOrderRepository productOrderRepository; // Thay Products_Order bằng Product
        OrderRepository orderRepository;
        OrderItemRepository orderItemRepository;
        OrderMapper orderMapper;
        AddressRepository addressRepository;
        SellerReponsitory sellerReponsitory;
        OrderPlacedRepository orderPlacedRepository;


        // tạo order và đẩy lên orderPlaced theo id seller
        @Transactional
        public List<OrderResponse> createOrder(OrderCreationRequest request) {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
            Address address = addressRepository.findById(request.getShippingAddress()).orElseThrow(() -> new AppException(ErrorCode.ADDRESS_NOT_FOUND));


            Map<Long, List<OrderItemCreationRequest>> productsBySeller = new HashMap<>();
            for (var item : request.getProducts_order()) {
                Products_Order product = productOrderRepository.findById(item.getId_products_order())
                        .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
                productsBySeller.computeIfAbsent(product.getId_seller(), k -> new ArrayList<>()).add(item);
            }


            List<OrderResponse> responses = new ArrayList<>();


            for (var entry : productsBySeller.entrySet()) {
                Order order = Order.builder()
                        .user(user)
                        .status(request.getStatus())
                        .paymentMethod(request.getPaymentMethod())
                        .shippingAddress(address)
                        .totalPrice(BigDecimal.ZERO)
                        .createdAt(LocalDate.now())
                        .updatedAt(LocalDate.now())
                        .items(new HashSet<>())
                        .build();



                Double totalPrice = 0.0;
                Long id_seller= 0L;
                for (var itemRequest : entry.getValue()) {
                    Products_Order product = productOrderRepository.findById(itemRequest.getId_products_order())
                            .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
                    OrderItem orderItem = OrderItem.builder()
                            .order(order)
                            .products_order(product)
                            .quantity(itemRequest.getQuantity())
                            .unitPrice(product.getPrice() * itemRequest.getQuantity())
                            .build();
                    totalPrice += orderItem.getUnitPrice() * orderItem.getQuantity();
                    order.getItems().add(orderItem);
                id_seller=product.getId_seller();
                }
                order.setTotalPrice(BigDecimal.valueOf(totalPrice));
                orderRepository.save(order);
                Seller seller =  sellerReponsitory.findById(id_seller).orElseThrow(() -> new AppException(ErrorCode.SELLER_NOT_FOUND));
                OrderPlaced orderPlaced = OrderPlaced.builder()
                        .id_order(order.getOrderId())
                        .seller(seller)
                        .build();
                orderPlacedRepository.save(orderPlaced);
                seller.getOrderPlaceds().add(orderPlaced);
                sellerReponsitory.save(seller);

                responses.add(orderMapper.toOrderResponse(order));
            }

            return responses;
        }

        public Set<OrderResponse> getOrder() {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
           Set<Order> order =user.getOrders();
            return order.stream().map(orderMapper::toOrderResponse).collect(Collectors.toSet());

        }

    }



