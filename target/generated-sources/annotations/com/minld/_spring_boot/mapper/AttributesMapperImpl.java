package com.minld._spring_boot.mapper;

import com.minld._spring_boot.dto.request.AttributesCreationRequest;
import com.minld._spring_boot.dto.response.AttributesResponse;
import com.minld._spring_boot.dto.response.AttributesValueResponse;
import com.minld._spring_boot.entity.Attributes;
import com.minld._spring_boot.entity.AttributesValues;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class AttributesMapperImpl implements AttributesMapper {

    @Override
    public Attributes toAttributes(AttributesCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Attributes.AttributesBuilder attributes = Attributes.builder();

        attributes.name( request.getName() );

        return attributes.build();
    }

    @Override
    public AttributesResponse toAttributesResponse(Attributes attributes) {
        if ( attributes == null ) {
            return null;
        }

        AttributesResponse.AttributesResponseBuilder attributesResponse = AttributesResponse.builder();

        attributesResponse.id( attributes.getId() );
        attributesResponse.name( attributes.getName() );

        return attributesResponse.build();
    }

    @Override
    public AttributesValueResponse toAttributesValueResponse(AttributesValues attributesValues) {
        if ( attributesValues == null ) {
            return null;
        }

        AttributesValueResponse.AttributesValueResponseBuilder attributesValueResponse = AttributesValueResponse.builder();

        attributesValueResponse.id( attributesValues.getId() );
        attributesValueResponse.name( attributesValues.getName() );
        attributesValueResponse.price( attributesValues.getPrice() );
        attributesValueResponse.quantity( attributesValues.getQuantity() );
        attributesValueResponse.image( attributesValues.getImage() );

        return attributesValueResponse.build();
    }
}
