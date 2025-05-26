package com.minld._spring_boot.service;

import com.minld._spring_boot.Repository.AddressRepository;
import com.minld._spring_boot.Repository.CategoriesRepository;
import com.minld._spring_boot.Repository.MediaReponsitory;
import com.minld._spring_boot.Repository.UserRepository;
import com.minld._spring_boot.dto.request.AddressCreationRequest;
import com.minld._spring_boot.dto.request.CategoriesCreationRequest;
import com.minld._spring_boot.dto.request.CategoriesUpdationRequest;
import com.minld._spring_boot.dto.response.AddressResponse;
import com.minld._spring_boot.dto.response.CategoriesResponse;
import com.minld._spring_boot.dto.response.ProductsResponse;
import com.minld._spring_boot.entity.*;
import com.minld._spring_boot.exception.AppException;
import com.minld._spring_boot.exception.ErrorCode;
import com.minld._spring_boot.mapper.AddressMapper;
import com.minld._spring_boot.mapper.CategoriesMapper;
import com.minld._spring_boot.mapper.ProductsMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddressService {
    private final UserRepository userRepository;
    AddressMapper addressMapper;
    AddressRepository addressRepository;

    public AddressResponse Create(AddressCreationRequest request) {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =
                userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Seller seller = user.getSeller();
        if (seller == null) {
            throw new AppException(ErrorCode.SELLER_NOT_FOUND);
        }

        Address address = addressMapper.toAddress(request);
        address.setSeller(seller);

        if (seller.getAddresses() == null || seller.getAddresses().isEmpty()) {
            address.setDefault(true); // Địa chỉ đầu tiên tự động là mặc định
        } else if (request.isDefault()) {
            for (Address existingAddress : seller.getAddresses()) {
                existingAddress.setDefault(false);
            }
            address.setDefault(true);
        }
        address = addressRepository.save(address);

        return addressMapper.toAddressResponse(address);
    }

    public void delete(Long id) {
        addressRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ADDRESS_NOT_FOUND));
        addressRepository.deleteById(id);
    }

    public AddressResponse update(Long id, AddressCreationRequest request) {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =
                userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Seller seller = user.getSeller();
        if (seller == null) {
            throw new AppException(ErrorCode.SELLER_NOT_FOUND);
        }
        Address address = addressRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ADDRESS_NOT_FOUND));
        addressMapper.updateAddress(address, request);

        if (request.isDefault()) {
            for (Address existingAddress : seller.getAddresses()) {
                if (!existingAddress.getId().equals(address.getId())) {
                    existingAddress.setDefault(false);
                    addressRepository.save(existingAddress); // Lưu thay đổi cho các địa chỉ khác
                }
            }
            address.setDefault(true);
        }
        log.info("Updated address {} with isDefault: {}", address.getId(), address.isDefault());
        return addressMapper.toAddressResponse(addressRepository.save(address));
    }



}
