// package com.minld._spring_boot.service;
//
// import com.minld._spring_boot.Repository.UserRepository;
// import com.minld._spring_boot.dto.request.UserCreationRequest;
// import com.minld._spring_boot.dto.response.UserResponse;
// import com.minld._spring_boot.entity.User;
// import com.minld._spring_boot.exception.AppException;
// import com.minld._spring_boot.exception.ErrorCode;
// import org.assertj.core.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
// import java.time.LocalDate;
//
// @SpringBootTest
// public class UserServiceTest {
//    @Autowired
//   private UserService userService;
//
//    @Mock
//    private UserRepository userRepository;
//
//    private MockMvc mockMvc;
//    private UserCreationRequest request;
//    private UserResponse userResponse;
//    private LocalDate dob;
//    private User user;
//
//    @BeforeEach
//    void initData() {
//        dob = LocalDate.parse("2000-01-01");
//        request = UserCreationRequest.builder()
//                .email("minld")
//                .firstName("Minh")
//                .lastName("Le")
//                .dob(dob)
//                .password("12345678")
//                .build();
//
//        userResponse = UserResponse.builder()
//                .id("1lm-23d-ffe-fdd-ds2")
//                .email("minld")
//                .firstName("Minh")
//                .lastName("Le")
//                .dob(dob)
//                .build();
//
//        user = User.builder()
//                .id("1lm-23d-ffe-fdd-ds2")
//                .email("minld")
//                .firstName("Minh")
//                .lastName("Le")
//                .dob(dob)
//                .build();
//
//        mockMvc = MockMvcBuilders.standaloneSetup(userService).build();
//    }
//
//    @Test
//    void createUser_validRequest_success() throws Exception {
//        // GIVEN
//        Mockito.when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
//        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
//
//        // WHEN
//        var response = userService.createUser(request);
//
//        //THEN
//        Assertions.assertThat(response.getEmail()).isEqualTo("minld");
//
//
//    }
//
//////    @Test
//////    void createUser_validRequest_fail() throws Exception {
//////        // GIVEN
//////        Mockito.when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);
//////
//////        //WHEN
//////        var exp = assertThrows(AppException.class,
//////                () -> userService.createUser(request));
//////
//////        //THEN
//////       Assertions.assertThat(exp.getErrorCode().getCode()).isEqualTo(1002);
//////
//////
//////
//////
//////
//////
//////    }
////
////
//// }
