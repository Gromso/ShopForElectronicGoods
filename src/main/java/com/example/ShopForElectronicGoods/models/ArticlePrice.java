package com.example.ShopForElectronicGoods.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "article_price")
public class ArticlePrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_price_id", nullable = false)
    private Integer article_price_id;


    @JsonBackReference
    @ManyToOne(targetEntity = Article.class)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    @NotNull
    private BigDecimal price;

    @Basic(optional = false)
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at_price;

}
