package com.example.ShopForElectronicGoods.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
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
    private String name;

    @Column (name = "image_path", nullable = false )
    private String image_path;

    @OneToMany(mappedBy = "category")
    private Set<Article> articles ;

    @OneToMany(mappedBy = "category")
    private Set<Feature> features;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parent_category_id;


}
