package com.example.ShopForElectronicGoods.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "feature")
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feature_id", nullable = false)
    private Integer featureId;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 128)
    private String name;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId",referencedColumnName = "category_id", nullable = false)
   // @JsonIgnore
    private Category category;

    @JsonManagedReference
    @OneToMany(mappedBy = "feature",cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Set<ArticleFeature> articleFeatureSet;
}
