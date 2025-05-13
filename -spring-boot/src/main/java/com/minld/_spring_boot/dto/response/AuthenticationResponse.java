package com.minld._spring_boot.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationResponse {
    String token;
    boolean authenticated;
    Boolean isActive;
}
