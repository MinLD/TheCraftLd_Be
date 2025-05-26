package com.minld._spring_boot.mapper;

import com.minld._spring_boot.dto.request.UserCreationRequest;
import com.minld._spring_boot.dto.request.UserUpdationRequest;
import com.minld._spring_boot.dto.response.PermissionResponse;
import com.minld._spring_boot.dto.response.RoleResponse;
import com.minld._spring_boot.dto.response.UserResponse;
import com.minld._spring_boot.entity.Permission;
import com.minld._spring_boot.entity.Role;
import com.minld._spring_boot.entity.User;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( request.getEmail() );
        user.password( request.getPassword() );
        user.fullName( request.getFullName() );

        return user.build();
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.email( user.getEmail() );
        userResponse.fullName( user.getFullName() );
        userResponse.code( user.getCode() );
        userResponse.isActive( user.getIsActive() );
        userResponse.profileUser( user.getProfileUser() );
        userResponse.roles( roleSetToRoleResponseSet( user.getRoles() ) );
        userResponse.seller( user.getSeller() );

        return userResponse.build();
    }

    @Override
    public void updateUser(User user, UserUpdationRequest request) {
        if ( request == null ) {
            return;
        }

        user.setEmail( request.getEmail() );
        user.setPassword( request.getPassword() );
        user.setFullName( request.getFullName() );
    }

    @Override
    public User toAdminCreateUser(String email, String password, String fullName) {
        if ( email == null && password == null && fullName == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( email );
        user.password( password );
        user.fullName( fullName );

        return user.build();
    }

    protected PermissionResponse permissionToPermissionResponse(Permission permission) {
        if ( permission == null ) {
            return null;
        }

        PermissionResponse.PermissionResponseBuilder permissionResponse = PermissionResponse.builder();

        permissionResponse.name( permission.getName() );
        permissionResponse.description( permission.getDescription() );

        return permissionResponse.build();
    }

    protected Set<PermissionResponse> permissionSetToPermissionResponseSet(Set<Permission> set) {
        if ( set == null ) {
            return null;
        }

        Set<PermissionResponse> set1 = new LinkedHashSet<PermissionResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Permission permission : set ) {
            set1.add( permissionToPermissionResponse( permission ) );
        }

        return set1;
    }

    protected RoleResponse roleToRoleResponse(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleResponse.RoleResponseBuilder roleResponse = RoleResponse.builder();

        roleResponse.name( role.getName() );
        roleResponse.description( role.getDescription() );
        roleResponse.permissions( permissionSetToPermissionResponseSet( role.getPermissions() ) );

        return roleResponse.build();
    }

    protected Set<RoleResponse> roleSetToRoleResponseSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleResponse> set1 = new LinkedHashSet<RoleResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleToRoleResponse( role ) );
        }

        return set1;
    }
}
