package com.example.ShopForElectronicGoods.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table (name = "article_feature")
public class ArticleFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_feature_id", nullable = false)
    private Integer article_feature_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "featureId",referencedColumnName = "feature_id",  nullable = false)
    private Feature feature;

    @Column(name = "value", nullable = false)
    @NotBlank
    @Size(min = 0, max = 255)
    private String value;


}
