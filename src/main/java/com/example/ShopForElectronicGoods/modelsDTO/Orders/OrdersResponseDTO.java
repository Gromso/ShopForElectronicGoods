package com.example.ShopForElectronicGoods.modelsDTO.Orders;


import com.example.ShopForElectronicGoods.models.ENUMS.OrderStatusEnum;
import com.example.ShopForElectronicGoods.modelsDTO.Cart.CartResponseDTO;
import jakarta.validation.constraints.Min;

import java.util.Date;

public class OrdersResponseDTO {

    @Min(0)
    private Integer order_id;
    private Date created_at_order;
    private OrderStatusEnum status;
    private CartResponseDTO cart;

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Date getCreated_at_order() {
        return created_at_order;
    }

    public void setCreated_at_order(Date created_at_order) {
        this.created_at_order = created_at_order;
    }

    public CartResponseDTO getCart() {
        return cart;
    }

    public void setCart(CartResponseDTO cart) {
        this.cart = cart;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }
}
