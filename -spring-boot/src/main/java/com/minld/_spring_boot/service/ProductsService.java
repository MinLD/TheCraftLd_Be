package com.minld._spring_boot.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.minld._spring_boot.Repository.SellerReponsitory;
import com.minld._spring_boot.dto.response.CategoriesResponse;
import com.minld._spring_boot.entity.*;
import com.minld._spring_boot.mapper.CategoriesMapper;
import jakarta.transaction.Transactional;
import jdk.jfr.Category;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.minld._spring_boot.Repository.CategoriesRepository;
import com.minld._spring_boot.Repository.ProductsRepository;
import com.minld._spring_boot.Repository.UserRepository;
import com.minld._spring_boot.dto.request.ProductsCreationRequest;
import com.minld._spring_boot.dto.request.ProductsUpdateRequest;
import com.minld._spring_boot.dto.response.ProductsResponse;
import com.minld._spring_boot.exception.AppException;
import com.minld._spring_boot.exception.ErrorCode;
import com.minld._spring_boot.mapper.ProductsMapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductsService {
    private final UserRepository userRepository;

    private final CategoriesRepository categoriesRepository;

    ProductsMapper productsMapper;

    ProductsRepository productsRepository;

    CloudinaryService cloudinaryService;

    CategoriesRepository categoryRepository;

    CategoriesMapper categoriesMapper;

    SellerReponsitory sellerReponsitory;


    public List<ProductsResponse> getTopViewProductsByCategory() {
        // Lấy tất cả danh mục
        List<Categories> categories = categoryRepository.findAll();

        // Danh sách để lưu tất cả top 5 sản phẩm từ các danh mục
        List<ProductsResponse> result = new ArrayList<>();

        for (Categories category : categories) {
            // Lấy top 5 sản phẩm theo views
            List<Products> topProducts = productsRepository.findTop5ByCategories_IdOrderByViewsDesc(category.getId());
            // Chuyển đổi sang ProductsResponse và thêm vào danh sách kết quả
            List<ProductsResponse> productResponses = topProducts.stream()
                    .map(productsMapper::toProductsResponse)
                    .collect(Collectors.toList());
            result.addAll(productResponses);
        }
        // Đảo lộn ngẫu nhiên danh sách kết quả
        Collections.shuffle(result);

        return result;
    }

    public ProductsResponse create(Long id_Category, ProductsCreationRequest request) {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (user.getSeller() == null) throw new AppException(ErrorCode.SELLER_EXIST);

        var categories = categoriesRepository
                .findById(id_Category)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        var seller = user.getSeller();

        Set<MediaFile> mediaFiles = new HashSet<>();
        if (request.getImages() != null) {
            try {
                List<MediaFile> uploadedFiles = new ArrayList<>();
                for (MultipartFile file : request.getImages()) {
                    MediaFile mediaFile = cloudinaryService.updateFile(file, "products", email);
                    uploadedFiles.add(mediaFile);
                }
                mediaFiles.addAll(uploadedFiles);
            } catch (AppException e) {
                log.error("Error uploading image", e);
                throw new AppException(ErrorCode.UPLOAD_FAILED);
            } catch (IOException e) {
                log.error("Error uploading image", e);
                throw new RuntimeException(e);
            }
        }

        Products products = Products.builder()
                .style(request.getStyle())
                .quantity(request.getQuantity())
                .material(request.getMaterial())
                .trademark(request.getTrademark())
                .origin(request.getOrigin())
                .id_Seller(seller.getId())
                .sku(categories.getId())
                .images(mediaFiles)
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .discount(request.getDiscount())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .seller(seller)
                .categories(categories)
                .build();
        productsRepository.save(products);


        // Đếm tổng số sản phẩm của seller (bao gồm sản phẩm vừa tạo)
        long totalProducts = productsRepository.countBySellerId(seller.getId());
        seller.setProductsCount((int) totalProducts);
        sellerReponsitory.save(seller);

        return productsMapper.toProductsResponse(products);
    }

    public ProductsResponse update(Long id_Product, ProductsUpdateRequest request) {
        Products products = productsRepository
                .findById(id_Product)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        productsMapper.updateProducts(products, request);

        return productsMapper.toProductsResponse(productsRepository.save(products));
    }

    public List<ProductsResponse> getAllProducts(Long id_Seller) {
        Seller seller = sellerReponsitory.findById(id_Seller).orElseThrow(() -> new AppException(ErrorCode.SELLER_NOT_FOUND));
        var products = seller.getProducts();




        // Ánh xạ danh sách sản phẩm sang danh sách ProductsResponse
        return products.stream().map(productsMapper::toProductsResponse).toList();
    }

    public Void delete(Long id) {
        productsRepository.deleteById(id);
        return null;
    }

    public List<ProductsResponse> getProductsBySellerAndCategory(Long id_Seller,Long categoriesId) {
       Seller seller = sellerReponsitory.findById(id_Seller).orElseThrow(() -> new AppException(ErrorCode.SELLER_NOT_FOUND));
        var sellerId = seller.getId();
        return productsRepository.findBySellerIdAndCategoriesId(sellerId, categoriesId).stream()
                .map(productsMapper::toProductsResponse)
                .toList();
    }

    @Transactional
    public ProductsResponse getProductsById(Long id) {
        var products = productsRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        products.setViews(products.getViews() != null ? products.getViews() + 1 : 1);

        return productsMapper.toProductsResponse(productsRepository.save(products));
    }



}
