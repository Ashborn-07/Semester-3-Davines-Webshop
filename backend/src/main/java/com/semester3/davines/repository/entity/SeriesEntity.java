package com.semester3.davines.repository.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "series")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeriesEntity extends BaseEntity {

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "image")
    private String image;

    @NotBlank
    @Length(min = 10, max = 1000)
    @Column(name = "description")
    private String description;
}
