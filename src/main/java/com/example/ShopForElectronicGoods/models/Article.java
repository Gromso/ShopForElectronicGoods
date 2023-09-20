package com.example.ShopForElectronicGoods.models;

import com.example.ShopForElectronicGoods.models.ENUMS.ArticleStatusEnum;
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
@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id", nullable = false)
    private Integer article_id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 0, max = 128)
    private String name;

    @OneToMany(mappedBy = "article")
    private Set<ArticleFeature> articleFeatureSet;

    @JsonManagedReference
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Photo> photos;

    @JsonManagedReference
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private Set<ArticlePrice> articlePrice;

    @OneToMany(mappedBy = "article", targetEntity = CartArticle.class)
    private Set<CartArticle> cartArticle;

    @Column (name = "excerpt", nullable = false)
    @NotBlank
    @Size(min = 0, max = 255)
    private String excerpt;

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 0, max = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column (name = "status", nullable = false)
    private ArticleStatusEnum status = ArticleStatusEnum.available;

    @Column(name = "is_promoted", columnDefinition = "TINYINT")
    private byte is_promoted;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
