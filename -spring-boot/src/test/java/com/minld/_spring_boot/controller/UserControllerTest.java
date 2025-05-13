// package com.minld._spring_boot.controller;
//
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
// import com.minld._spring_boot.dto.request.UserCreationRequest;
// import com.minld._spring_boot.dto.response.UserResponse;
// import com.minld._spring_boot.service.UserService;
// import lombok.extern.slf4j.Slf4j;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.ArgumentMatchers;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
// import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
// import java.time.LocalDate;
// import java.util.HashMap;
// import java.util.Map;
//
// @ExtendWith(MockitoExtension.class)
// @Slf4j
// public class UserControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private UserService userService;
//
//    @InjectMocks
//    private UserController userController;
//
//    private UserCreationRequest request;
//    private UserResponse userResponse;
//    private LocalDate dob;
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
//        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//    }
//
//    @Test
//    void createUser_validRequest_success() throws Exception {
//        // GIVEN
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        String content = objectMapper.writeValueAsString(request);
//
//        Mockito.when(userService.createUser(ArgumentMatchers.any())).thenReturn(userResponse);
//
//        // WHEN & THEN
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/users")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(content))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("code").value("1000"))
//                .andExpect(MockMvcResultMatchers.jsonPath("result.id").value("1lm-23d-ffe-fdd-ds2"))
//                .andExpect(MockMvcResultMatchers.jsonPath("result.id").value(userResponse.getId()))
//                .andExpect(MockMvcResultMatchers.jsonPath("result.email").value(userResponse.getEmail()));
//    }
// }
