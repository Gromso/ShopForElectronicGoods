package com.example.ShopForElectronicGoods.models;

import com.example.ShopForElectronicGoods.models.ENUMS.ArticleStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id", nullable = false)
    private Integer article_id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "article")
    private Set<ArticleFeature> articleFeatureSet;

    @OneToMany(mappedBy = "article")
    private Set<Photo> photos;

    @OneToMany(mappedBy = "article")
    private Set<ArticlePrice> articlePrice;

    @OneToMany(mappedBy = "article")
    private Set<CartArticle> cartArticle;

    @Column (name = "excerpt", nullable = false)
    private String excerpt;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column (name = "status", nullable = false)
    private ArticleStatusEnum status = ArticleStatusEnum.available;

    @Column(name = "is_promoted", columnDefinition = "TINYINT")
    private byte is_promoted;

}
