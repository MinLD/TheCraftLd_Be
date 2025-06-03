package com.minld._spring_boot.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String fullName;
    private String email;

    private String address;
    private String city;

    @Builder.Default
    private String country = "VIETNAM";

    private String phone;
    private LocalDate dob;
    private String gender;

    //    @OneToOne
    //    @JoinColumn(name = "avatar_id")
    //    MediaFile avatar;

}
