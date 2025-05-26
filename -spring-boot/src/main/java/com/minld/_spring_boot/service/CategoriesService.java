package com.minld._spring_boot.service;

import com.minld._spring_boot.Repository.*;
import com.minld._spring_boot.dto.request.CategoriesCreationRequest;
import com.minld._spring_boot.dto.request.CategoriesUpdationRequest;
import com.minld._spring_boot.dto.response.CategoriesResponse;
import com.minld._spring_boot.dto.response.ProductsResponse;
import com.minld._spring_boot.entity.*;
import com.minld._spring_boot.exception.AppException;
import com.minld._spring_boot.exception.ErrorCode;
import com.minld._spring_boot.mapper.CategoriesMapper;
import com.minld._spring_boot.mapper.ProductsMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoriesService {
    CategoriesRepository categoriesRepository;
    CategoriesMapper categoriesMapper;
    ProductsMapper productsMapper;
    CloudinaryService cloudinaryService;
    MediaReponsitory mediaReponsitory;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CategoriesResponse create(CategoriesCreationRequest request)  {
        if(categoriesRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.CATEGORY_EXISTED);
        }

        MediaFile mediaFile = null;
        if (request.getImage() != null) {
            try {
                mediaFile = cloudinaryService.updateFile(request.getImage(), "categories", request.getName());
            mediaReponsitory.save(mediaFile);
            }catch (AppException | IOException e){
                throw new AppException(ErrorCode.UPLOAD_FAILED);
            }
        }
        var categories =  categoriesRepository.save(Categories.builder()
                        .name(request.getName())
                        .description(request.getDescription())
                        .image(mediaFile)
                        .createdAt(LocalDate.now())
                        .updatedAt(LocalDate.now())
                .build()
        );

       return categoriesMapper.toCategoriesResponse(categories);
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<CategoriesResponse> GetAllCategories() {
        var categories = categoriesRepository.findAll();
        return categories.stream().map(categoriesMapper::toCategoriesResponse).toList();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(Long userId) {
        categoriesRepository.deleteById(userId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CategoriesResponse update(Long userId, CategoriesUpdationRequest request) {
        var categories = categoriesRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        MediaFile mediaFile = null;
        if (request.getImage() != null) {
            try {
                mediaFile = cloudinaryService.updateFile(request.getImage(), "categories", request.getName());
                mediaReponsitory.save(mediaFile);
            }catch (AppException | IOException e){
                throw new AppException(ErrorCode.UPLOAD_FAILED);
            }
        }
        categories.setImage(mediaFile);
        categoriesMapper.updateCategories(categories, request);
        return categoriesMapper.toCategoriesResponse(categoriesRepository.save(categories));
    }

    public List<ProductsResponse> getProductsCategories(Long userId) {
        var categories = categoriesRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        var products = categories.getProducts();

        return  products.stream()
                .map(productsMapper::toProductsResponse)
                .toList();
    }




}
