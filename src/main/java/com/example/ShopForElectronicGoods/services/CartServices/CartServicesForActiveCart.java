package com.example.ShopForElectronicGoods.services.CartServices;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.Cart;
import com.example.ShopForElectronicGoods.models.Orders;
import com.example.ShopForElectronicGoods.modelsDTO.Cart.CartResponseDTO;
import com.example.ShopForElectronicGoods.modelsDTO.Orders.OrdersResponseDTO;
import com.example.ShopForElectronicGoods.repository.OrderRepository;
import com.example.ShopForElectronicGoods.services.CartService;
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

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderRepository ordersRepository;

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


        public OrdersResponseDTO getOrdersResponse(Integer cartId, Integer userId, Integer orderId) {
            CartResponseDTO cart = cartService.getListCartArticles(cartId, userId);
            Orders orders = ordersRepository.findById(orderId).orElseThrow(() -> new ApiRequestException("order by order ID " + orderId + " not found"));

            OrdersResponseDTO ordersResponseDTO = new OrdersResponseDTO();
            ordersResponseDTO.setOrder_id(orders.getOrder_id());
            ordersResponseDTO.setCreated_at_order(orders.getCreated_at_order());
            ordersResponseDTO.setStatus(orders.getStatus());
            ordersResponseDTO.setCart(cart);
            return ordersResponseDTO;

        }

}
