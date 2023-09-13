package com.example.ShopForElectronicGoods.services.CartServices;
import com.example.ShopForElectronicGoods.models.Cart;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServicesForActiveCart {


    @PersistenceContext
    @Autowired
    private EntityManager entityManager;


    public Cart getLastActiveCartByUserId(Integer userId) {
        String jpql = "SELECT c FROM Cart c " +
                "WHERE c.user.id = :userId " +
                "ORDER BY c.created_at_cart DESC";

        TypedQuery<Cart> query = entityManager.createQuery(jpql, Cart.class);
        query.setParameter("userId", userId);
        query.setMaxResults(1);

        List<Cart> carts = query.getResultList();

        if (carts.isEmpty()) {
            return null;
        } else {
            return carts.get(0);
        }
    }



}
