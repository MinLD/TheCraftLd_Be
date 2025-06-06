package com.minld._spring_boot.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.minld._spring_boot.Repository.AttributesRepository;
import com.minld._spring_boot.Repository.AttributesValueRepository;
import com.minld._spring_boot.Repository.MediaReponsitory;
import com.minld._spring_boot.Repository.ProductsRepository;
import com.minld._spring_boot.dto.request.AttributesCreationRequest;
import com.minld._spring_boot.dto.request.AttributesValuesCreationRequest;
import com.minld._spring_boot.dto.response.AttributesResponse;
import com.minld._spring_boot.dto.response.AttributesValueResponse;
import com.minld._spring_boot.entity.*;
import com.minld._spring_boot.exception.AppException;
import com.minld._spring_boot.exception.ErrorCode;
import com.minld._spring_boot.mapper.AttributesMapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AttributesService {
    private final AttributesValueRepository attributesValuesRepository;
    ProductsRepository productsRepository;
    AttributesMapper attributesMapper;
    AttributesRepository attributesRepository;
    CloudinaryService cloudinaryService;
    MediaReponsitory mediaReponsitory;

    public AttributesResponse CreateAttributes(Long id_Product, AttributesCreationRequest request) {
        Products products = productsRepository
                .findById(id_Product)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        Attributes attributes =
                Attributes.builder().name(request.getName()).products(products).build();
        attributes = attributesRepository.save(attributes);

        products.getAttributes().add(attributes);

        productsRepository.save(products);

        return attributesMapper.toAttributesResponse(attributes);
    }

    public AttributesValueResponse CreateAttributesValues(Long id_Attributes, AttributesValuesCreationRequest request)
            throws IOException {
        Attributes attributes = attributesRepository
                .findById(id_Attributes)
                .orElseThrow(() -> new AppException(ErrorCode.ATTRIBUTE_NOT_FOUND));
        AttributesValues attributesValues = AttributesValues.builder()
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .attributes(attributes)
                .build();
        if (request.getImage() != null) {
            MediaFile mediaFile = null;
            try {
                mediaFile = cloudinaryService.updateFile(
                        request.getImage(), "attributesValues", attributesValues.getName());
                mediaReponsitory.save(mediaFile);
            } catch (AppException e) {
                throw new AppException(ErrorCode.UPLOAD_FAILED);
            }
            attributesValues.setImage(mediaFile);
        }
        attributesValues = attributesValuesRepository.save(attributesValues);
        attributes.getAttributesValues().add(attributesValues);
        attributesRepository.save(attributes);
        return attributesMapper.toAttributesValueResponse(attributesValues);
    }
}
