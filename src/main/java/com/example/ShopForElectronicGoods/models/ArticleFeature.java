package com.example.ShopForElectronicGoods.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feature_id", nullable = false)
    private Feature feature;

    @Column(name = "value", nullable = false)
    private String value;


}
