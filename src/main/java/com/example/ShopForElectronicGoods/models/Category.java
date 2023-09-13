package com.example.ShopForElectronicGoods.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer category_id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min =2, max = 64)
    private String name;

    @Column (name = "image_path", nullable = false )
    @Size(min = 2, max = 128)
    private String image_path;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private Set<Article> articles;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private Set<Feature> features;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    @JsonIgnore
    private Category parent_category;

    /*@OneToMany(mappedBy = "parent_category")
    @JsonIgnore
    private Set<Category> categories;*/

    public Integer getParentCategoryId() {
        if (parent_category != null) {
            return parent_category.getCategory_id();
        }
        return null;
    }



}
