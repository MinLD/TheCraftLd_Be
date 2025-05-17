package com.minld._spring_boot.mapper;

import com.minld._spring_boot.dto.request.SellerUpdationRequest;
import com.minld._spring_boot.dto.response.SellerResponse;
import com.minld._spring_boot.entity.MediaFile;
import com.minld._spring_boot.entity.Seller;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class SellerMapperImpl implements SellerMapper {

    @Override
    public Seller toSeller(SellerUpdationRequest request) {
        if ( request == null ) {
            return null;
        }

        Seller.SellerBuilder seller = Seller.builder();

        seller.name( request.getName() );
        seller.description( request.getDescription() );
        seller.taxCode( request.getTaxCode() );
        seller.image( multipartFileToMediaFile( request.getImage() ) );
        seller.updatedAt( request.getUpdatedAt() );

        return seller.build();
    }

    @Override
    public SellerResponse toSellerResponse(Seller seller) {
        if ( seller == null ) {
            return null;
        }

        SellerResponse.SellerResponseBuilder sellerResponse = SellerResponse.builder();

        sellerResponse.id( seller.getId() );
        sellerResponse.name( seller.getName() );
        sellerResponse.description( seller.getDescription() );
        sellerResponse.image( seller.getImage() );
        sellerResponse.taxCode( seller.getTaxCode() );
        sellerResponse.createdAt( seller.getCreatedAt() );
        sellerResponse.updatedAt( seller.getUpdatedAt() );

        return sellerResponse.build();
    }

    @Override
    public void updateSeller(Seller seller, SellerUpdationRequest request) {
        if ( request == null ) {
            return;
        }

        if ( request.getName() != null ) {
            seller.setName( request.getName() );
        }
        if ( request.getDescription() != null ) {
            seller.setDescription( request.getDescription() );
        }
        if ( request.getTaxCode() != null ) {
            seller.setTaxCode( request.getTaxCode() );
        }
        if ( request.getImage() != null ) {
            if ( seller.getImage() == null ) {
                seller.setImage( MediaFile.builder().build() );
            }
            multipartFileToMediaFile1( request.getImage(), seller.getImage() );
        }
        if ( request.getUpdatedAt() != null ) {
            seller.setUpdatedAt( request.getUpdatedAt() );
        }
    }

    protected MediaFile multipartFileToMediaFile(MultipartFile multipartFile) {
        if ( multipartFile == null ) {
            return null;
        }

        MediaFile.MediaFileBuilder mediaFile = MediaFile.builder();

        return mediaFile.build();
    }

    protected void multipartFileToMediaFile1(MultipartFile multipartFile, MediaFile mappingTarget) {
        if ( multipartFile == null ) {
            return;
        }
    }
}
