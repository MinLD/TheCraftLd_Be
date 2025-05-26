package com.minld._spring_boot.mapper;

import com.minld._spring_boot.dto.request.ProductsCreationRequest;
import com.minld._spring_boot.dto.response.ProductsResponse;
import com.minld._spring_boot.entity.MediaFile;
import com.minld._spring_boot.entity.Products;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class ProductsMapperImpl implements ProductsMapper {

    @Override
    public Products toProducts(ProductsCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Products.ProductsBuilder products = Products.builder();

        products.title( request.getTitle() );
        products.description( request.getDescription() );
        products.price( request.getPrice() );
        products.discount( request.getDiscount() );
        products.trademark( request.getTrademark() );
        products.origin( request.getOrigin() );
        products.style( request.getStyle() );
        products.quantity( request.getQuantity() );
        products.material( request.getMaterial() );
        products.images( multipartFileListToMediaFileSet( request.getImages() ) );

        return products.build();
    }

    @Override
    public ProductsResponse toProductsResponse(Products products) {
        if ( products == null ) {
            return null;
        }

        ProductsResponse.ProductsResponseBuilder productsResponse = ProductsResponse.builder();

        productsResponse.id( products.getId() );
        productsResponse.title( products.getTitle() );
        productsResponse.description( products.getDescription() );
        productsResponse.price( products.getPrice() );
        productsResponse.discount( products.getDiscount() );
        productsResponse.sku( products.getSku() );
        productsResponse.trademark( products.getTrademark() );
        productsResponse.origin( products.getOrigin() );
        productsResponse.style( products.getStyle() );
        productsResponse.quantity( products.getQuantity() );
        productsResponse.material( products.getMaterial() );
        Set<MediaFile> set = products.getImages();
        if ( set != null ) {
            productsResponse.images( new LinkedHashSet<MediaFile>( set ) );
        }
        productsResponse.seller( products.getSeller() );

        return productsResponse.build();
    }

    protected MediaFile multipartFileToMediaFile(MultipartFile multipartFile) {
        if ( multipartFile == null ) {
            return null;
        }

        MediaFile.MediaFileBuilder mediaFile = MediaFile.builder();

        return mediaFile.build();
    }

    protected Set<MediaFile> multipartFileListToMediaFileSet(List<MultipartFile> list) {
        if ( list == null ) {
            return null;
        }

        Set<MediaFile> set = new LinkedHashSet<MediaFile>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( MultipartFile multipartFile : list ) {
            set.add( multipartFileToMediaFile( multipartFile ) );
        }

        return set;
    }
}
