package com.minld._spring_boot.entity;


import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "email", unique = true,nullable = false)
    String email;

    @Column(name = "password" , nullable = false)
    @JsonIgnore
    String password;

    @Column(name = "fullName" )
    String fullName;

    @Column(name = "isActive" )
    Boolean isActive ;

    @Column(name = "code" )
    String code;

    @OneToOne(mappedBy = "user" , cascade = CascadeType.ALL , orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    ProfileUser profileUser;


    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    Seller seller;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role")
    Set<Role> roles = new HashSet<>();
}










//cascade = CascadeType.ALL:
//Chỉ định rằng tất cả các thao tác trên thực thể User (như lưu, cập nhật, xóa, v.v.)
// sẽ được tự động áp dụng (cascade) sang thực thể ProfileUser liên quan.

//orphanRemoval = true:
//Khi được đặt là true, nếu một ProfileUser bị gỡ bỏ khỏi mối quan hệ với User
// (tức là profileUser được đặt thành null hoặc thay thế bằng một ProfileUser khác)
// , thì ProfileUser bị gỡ bỏ sẽ tự động bị xóa khỏi cơ sở dữ liệu.
//Điều này đảm bảo rằng không còn các bản ghi "mồ côi" (orphaned records)
// trong bảng ProfileUser mà không có mối quan hệ với User.

//LAZY giúp cải thiện hiệu suất bằng cách tránh tải dữ liệu không cần thiết, đặc biệt
// khi ProfileUser chứa nhiều dữ liệu hoặc không phải lúc nào cũng cần.
//Lưu ý: Nếu bạn truy cập profileUser trong một phiên giao dịch (session)
// đã đóng, có thể gây ra lỗi LazyInitializationException trừ khi bạn sử dụng
// các cơ chế như Hibernate.initialize() hoặc cấu hình khác.