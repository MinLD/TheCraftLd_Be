package com.minld._spring_boot.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.minld._spring_boot.Repository.ProfileReponsitory;
import com.minld._spring_boot.Repository.RoleReponsitory;
import com.minld._spring_boot.Repository.SellerReponsitory;
import com.minld._spring_boot.Repository.UserRepository;
import com.minld._spring_boot.constant.PredefindRole;
import com.minld._spring_boot.dto.request.ActiveUserRequest;
import com.minld._spring_boot.dto.request.AdminCreationUsersRequest;
import com.minld._spring_boot.dto.request.UserCreationRequest;
import com.minld._spring_boot.dto.request.UserUpdationRequest;
import com.minld._spring_boot.dto.response.UserResponse;
import com.minld._spring_boot.entity.ProfileUser;
import com.minld._spring_boot.entity.Role;
import com.minld._spring_boot.entity.Seller;
import com.minld._spring_boot.entity.User;
import com.minld._spring_boot.exception.AppException;
import com.minld._spring_boot.exception.ErrorCode;
import com.minld._spring_boot.mapper.UserMapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    UserMapper userMapper;
    RoleReponsitory roleReponsitory;
    ProfileReponsitory profileReponsitory;
    ProfileService profileService;
    EmailService emailService;
    SellerReponsitory sellerReponsitory;

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        // Tạo User
        User user = userMapper.toUser(request);
        Random random = new Random();
        String code = String.format("%06d", random.nextInt(999999));
        user.setCode(code);
        user.setIsActive(false);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Tạo ProfileUser
        ProfileUser profileUser = ProfileUser.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .user(user) // Thiết lập mối quan hệ
                .build();

        // Gán ProfileUser cho User
        user.setProfileUser(profileUser);

        // Lưu User (sẽ tự động lưu ProfileUser do cascade = CascadeType.ALL)
        User savedUser = userRepository.save(user);

        // Gửi email xác thực
        emailService.sendEmailVerify(request.getEmail(), code);

        // Trả về UserResponse
        return userMapper.toUserResponse(savedUser);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserResponse createAdmin(AdminCreationUsersRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        User user = userMapper.toAdminCreateUser(request.getEmail(), request.getPassword(), request.getFullName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setIsActive(true);

        // Gán role
        HashSet<Role> roles = new HashSet<>();

        roleReponsitory.findById(request.getRole()).ifPresent(roles::add);
        if (roles.isEmpty()) throw new AppException(ErrorCode.ROLES_NOT_FOUND);
        user.setRoles(roles);
        // Tạo ProfileUser
        ProfileUser profileUser = ProfileUser.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .city(request.getCity())
                .address(request.getAddress())
                .phone(request.getPhone())
                .country(request.getCountry())
                .dob(request.getDob())
                .gender(request.getGender())
                .user(user) // Thiết lập mối quan hệ
                .build();
        // Gán ProfileUser cho User
        user.setProfileUser(profileUser);

        userRepository.save(user);
        if ("SELLER".equals(request.getRole())) { // So sánh tên role
            Seller seller = sellerReponsitory.save(Seller.builder()
                    .createdAt(LocalDate.now())
                    .image(null)
                    .updatedAt(LocalDate.now())
                    .description("Chưa cập nhật")
                    .name("Chưa cập nhật")
                    .taxCode("Chưa cập nhật")
                    .user(user)
                    .build());
            user.setSeller(seller);
            log.info("Seller created: {}", seller);
        }

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public String sendCodeUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Random random = new Random();
        String code = String.format("%06d", random.nextInt(999999));
        user.setCode(code);
        userRepository.save(user);
        emailService.sendEmailVerify(email, code);
        return "Send code successfully";
    }

    public String ActiveUser(ActiveUserRequest request) {
        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (!user.getCode().equals(request.getCode())) {
            throw new AppException(ErrorCode.INVALID_CODE);
        }
        user.setIsActive(true);
        // Gán role
        HashSet<Role> roles = new HashSet<>();
        roleReponsitory.findById(PredefindRole.USER_ROLE).ifPresent(roles::add);
        user.setRoles(roles);
        userRepository.save(user);

        return "User active successfully";
    }

    public UserResponse getMyInfo() {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }

    //    @PreAuthorize("hasAuthority('TAO')")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserResponse> getUsers() {
        log.info("Get all users");
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    //    @PostAuthorize("returnObject.email == authentication.name")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(
                userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    public UserResponse updateUser(String userId, UserUpdationRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleReponsitory.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
