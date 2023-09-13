package com.example.ShopForElectronicGoods.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Integer feature_id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 128)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
   // @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "feature")
    private Set<ArticleFeature> articleFeatureSet;
}
