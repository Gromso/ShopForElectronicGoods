package com.example.ShopForElectronicGoods.services;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.ENUMS.OrderStatusEnum;
import com.example.ShopForElectronicGoods.models.Order;
import com.example.ShopForElectronicGoods.repository.CartRepository;
import com.example.ShopForElectronicGoods.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;


    public Order getOrderById(Integer orderId){
        return orderRepository.findById(orderId).orElseThrow(()
                -> new ApiRequestException("Order by id "+ orderId + " not found", HttpStatus.NOT_FOUND));
    }

    public List<Order> getOrderList(){
        return orderRepository.findAll();
    }


    public Order addOrderByCartId(Order order, final Integer cartId){
        Order order2 = cartRepository.findById(cartId).map(cart -> {
            order.setCart(cart);
           return orderRepository.save(order);
        }).orElseThrow(() -> new ApiRequestException("Cart by " + cartId + " not Found", HttpStatus.NOT_FOUND));
        return orderRepository.save(order2);
    }


    public Order editOrder(Order order, final Integer orderId){
        Order order2 = getOrderById(orderId);

        if(Objects.nonNull(order.getStatus())){
            try{
                OrderStatusEnum status = order.getStatus();
                order2.setStatus(status);
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }
        }
        return orderRepository.save(order2);
    }

    public void deleteOrderById(final Integer orderId){
        orderRepository.deleteById(orderId);
    }


}
