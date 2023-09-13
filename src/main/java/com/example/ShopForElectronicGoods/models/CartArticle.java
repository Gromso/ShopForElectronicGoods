package com.example.ShopForElectronicGoods.models;
import com.example.ShopForElectronicGoods.modelsDTO.CartArticleDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cart_article")
public class CartArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_article_id", nullable = false)
    private Integer cart_article_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @Column(name = "quantity", nullable = false)
    @Min(0)
    private int quantity;

    public static CartArticle from (CartArticleDTO cartArticleDTO){
        CartArticle cartArticle = new CartArticle();
        cartArticle.setCart_article_id(cartArticleDTO.getCart_article_id());
        cartArticle.setCart(cartArticleDTO.getCart());
        cartArticle.setArticle(cartArticleDTO.getArticle());
        cartArticle.setQuantity(cartArticleDTO.getQuantity());
        return cartArticle;
    }

}
