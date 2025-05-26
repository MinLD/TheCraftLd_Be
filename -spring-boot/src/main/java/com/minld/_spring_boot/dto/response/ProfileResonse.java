package com.minld._spring_boot.dto.response;

import com.minld._spring_boot.entity.MediaFile;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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
