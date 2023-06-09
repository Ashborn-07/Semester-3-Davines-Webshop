package com.semester3.davines.repository.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "user")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    @NotBlank
    @Length(min = 2, max = 20)
    @Column(name = "email")
    private String email;

    @Length(max = 70)
    @Column(name = "first_name")
    private String firstName;

    @Length(max = 70)
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Column(name = "birthday")
    private String birthday;

    @NotBlank
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    @Length(max = 100)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Set<UserRoleEntity> userRoles;
}
