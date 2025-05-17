package com.minld._spring_boot.service;

import com.minld._spring_boot.Repository.*;
import com.minld._spring_boot.constant.PredefindRole;
import com.minld._spring_boot.dto.request.ProfileUpdationRequest;
import com.minld._spring_boot.dto.request.SellerCreationRequest;
import com.minld._spring_boot.dto.request.SellerUpdationRequest;
import com.minld._spring_boot.dto.response.ProfileResonse;
import com.minld._spring_boot.dto.response.SellerResponse;
import com.minld._spring_boot.entity.*;
import com.minld._spring_boot.exception.AppException;
import com.minld._spring_boot.exception.ErrorCode;
import com.minld._spring_boot.mapper.ProfileMapper;
import com.minld._spring_boot.mapper.SellerMapper;
import com.minld._spring_boot.mapper.UserMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SellerService {
    private final RoleReponsitory roleReponsitory;
    private final MediaReponsitory mediaReponsitory;
    private final UserRepository userRepository;
    UserMapper userMapper;
    SellerMapper sellerMapper;
    SellerReponsitory sellerReponsitory;
    CloudinaryService cloudinaryService;



    public SellerResponse create(SellerCreationRequest request) throws IOException {


        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =
                userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if(user.getSeller()!=null)
            throw new AppException(ErrorCode.SELLER_EXIST);



        MediaFile mediaFile = null;

        if (request.getImage() != null) {
        try {
            mediaFile = cloudinaryService.updateFile(request.getImage(), "seller", email);
            log.info(mediaFile.getUrl());
            mediaReponsitory.save(mediaFile);
        }catch (AppException e){
            throw new AppException(ErrorCode.UPLOAD_FAILED);
        }
        }
        Seller seller = Seller.builder()
                .user(user)
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .image(mediaFile)
                .description(request.getDescription())
                .name(request.getName())
                .taxCode(request.getTaxCode())
                .build();
        sellerReponsitory.save(seller);
        HashSet<Role> roles = new HashSet<>();
        roleReponsitory.findById(PredefindRole.SELLER_ROLE).ifPresent(roles::add);
        user.setRoles(roles);
        user.setSeller(seller);
        userRepository.save(user);
        return new SellerResponse(seller.getId(), seller.getName(), seller.getDescription(), seller.getImage(), seller.getTaxCode(), seller.getCreatedAt(), seller.getUpdatedAt());
    }

    public SellerResponse update(SellerUpdationRequest request) throws IOException {

        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =
                userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if(user.getSeller()==null)
            throw new AppException(ErrorCode.SELLER_NOT_FOUND);
        MediaFile mediaFile = null;
        Seller seller = user.getSeller();
        if(request.getImage()!=null){
            mediaFile = cloudinaryService.updateFile(request.getImage(), "seller", email);
            mediaReponsitory.save(mediaFile);
            seller.setImage(mediaFile);
        }
        seller.setUpdatedAt(LocalDate.now());

        sellerMapper.updateSeller(seller, request);

        return  sellerMapper.toSellerResponse(sellerReponsitory.save(seller));
    }
}
