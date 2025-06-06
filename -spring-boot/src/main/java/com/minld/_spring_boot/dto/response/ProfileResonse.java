package com.minld._spring_boot.dto.response;

import java.time.LocalDate;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileResonse {
    Long id;
    String fullName;
    String email;
    String address;
    String city;
    String country;
    String phone;
    LocalDate dob;
    String gender;
    //        MediaFile avatar;
}
