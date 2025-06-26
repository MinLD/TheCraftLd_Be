package com.minld._spring_boot.mapper;

import com.minld._spring_boot.dto.request.AddressCreationRequest;
import com.minld._spring_boot.dto.response.AddressResponse;
import com.minld._spring_boot.entity.Address;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address toAddress(AddressCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.name( request.getName() );
        address.phone( request.getPhone() );
        address.address( request.getAddress() );
        address.detailsAddress( request.getDetailsAddress() );
        address.isType( request.getIsType() );

        return address.build();
    }

    @Override
    public AddressResponse toAddressResponse(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressResponse.AddressResponseBuilder addressResponse = AddressResponse.builder();

        addressResponse.id( address.getId() );
        addressResponse.name( address.getName() );
        addressResponse.phone( address.getPhone() );
        addressResponse.address( address.getAddress() );
        addressResponse.detailsAddress( address.getDetailsAddress() );
        addressResponse.isType( address.getIsType() );

        return addressResponse.build();
    }

    @Override
    public void updateAddress(Address address, AddressCreationRequest request) {
        if ( request == null ) {
            return;
        }

        if ( request.getName() != null ) {
            address.setName( request.getName() );
        }
        if ( request.getPhone() != null ) {
            address.setPhone( request.getPhone() );
        }
        if ( request.getAddress() != null ) {
            address.setAddress( request.getAddress() );
        }
        if ( request.getDetailsAddress() != null ) {
            address.setDetailsAddress( request.getDetailsAddress() );
        }
        address.setAddressDefault( request.isAddressDefault() );
        if ( request.getIsType() != null ) {
            address.setIsType( request.getIsType() );
        }
    }
}
