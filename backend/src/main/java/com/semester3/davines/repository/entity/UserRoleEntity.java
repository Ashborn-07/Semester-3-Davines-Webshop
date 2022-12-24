package com.semester3.davines.repository.entity;

import com.semester3.davines.repository.entity.enums.UserRoleEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_role")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleEntity extends BaseEntity {

    @NotNull
    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private UserEntity user;
}
