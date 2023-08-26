package com.example.ShopForElectronicGoods.models;
import com.example.ShopForElectronicGoods.modelsDTO.Cart.CartDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    private Integer cart_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore // ovo mora da bude @JsonIgnore inace izbacuje (Cannot call sendError() after the response has been committed)
    private ApplicationUser user;

    @OneToMany(mappedBy = "cart")
    @JsonIgnore
    private Set<CartArticle> cartArticle;

    @Basic(optional = false)
    @Column(name = "created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at_cart;

    public static Cart from(CartDTO cartDTO){
        Cart cart = new Cart();
        cart.setCart_id(cart.getCart_id());
        cart.setUser(cartDTO.getUser());
        cart.setCartArticle(cartDTO.getCartArticle());
        cart.setCreated_at_cart(cartDTO.getCreated_at_cart());
        return cart;
    }
}
