package com.minld._spring_boot.mapper;

import com.minld._spring_boot.dto.request.CategoriesCreationRequest;
import com.minld._spring_boot.dto.request.CategoriesUpdationRequest;
import com.minld._spring_boot.dto.response.CategoriesResponse;
import com.minld._spring_boot.entity.Categories;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class CategoriesMapperImpl implements CategoriesMapper {

    @Override
    public Categories toCategories(CategoriesCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Categories.CategoriesBuilder categories = Categories.builder();

        categories.name( request.getName() );
        categories.description( request.getDescription() );

        return categories.build();
    }

    @Override
    public CategoriesResponse toCategoriesResponse(Categories categories) {
        if ( categories == null ) {
            return null;
        }

        CategoriesResponse.CategoriesResponseBuilder categoriesResponse = CategoriesResponse.builder();

        categoriesResponse.id( categories.getId() );
        categoriesResponse.name( categories.getName() );
        categoriesResponse.description( categories.getDescription() );
        categoriesResponse.createdAt( categories.getCreatedAt() );
        categoriesResponse.updatedAt( categories.getUpdatedAt() );

        return categoriesResponse.build();
    }

    @Override
    public void updateCategories(Categories categories, CategoriesUpdationRequest request) {
        if ( request == null ) {
            return;
        }

        if ( request.getName() != null ) {
            categories.setName( request.getName() );
        }
        if ( request.getDescription() != null ) {
            categories.setDescription( request.getDescription() );
        }
        if ( request.getUpdatedAt() != null ) {
            categories.setUpdatedAt( request.getUpdatedAt() );
        }
    }
}
