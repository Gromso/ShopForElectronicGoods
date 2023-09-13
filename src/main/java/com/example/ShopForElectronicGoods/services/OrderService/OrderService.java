package com.example.ShopForElectronicGoods.services.OrderService;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.ENUMS.OrderStatusEnum;
import com.example.ShopForElectronicGoods.models.Orders;
import com.example.ShopForElectronicGoods.repository.CartRepository;
import com.example.ShopForElectronicGoods.repository.OrderRepository;
import com.example.ShopForElectronicGoods.services.CartServices.CartServicesForActiveCart;
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

    @Autowired
    private CartServicesForActiveCart cartServicesForActiveCart;



    public Orders getOrderById(Integer orderId){
        return orderRepository.findById(orderId).orElseThrow(()
                -> new ApiRequestException("Order by id "+ orderId + " not found", HttpStatus.NOT_FOUND));
    }

    public List<Orders> getOrderList(){
        return orderRepository.findAll();
    }

    public List<Orders> getOrdersByUserId(Integer userId){
        List<Orders> findOrdersByUserId = orderRepository.findByCartUser(userId);
        return findOrdersByUserId;
    }

    public Orders addOrderByCartId(final Integer cartId){
        Orders orders = new Orders();
        List<Orders> orders1 = orderRepository.getOrderByCartId(cartId);
        if(!orders1.isEmpty()){
          throw new ApiRequestException("Your order has been sent", HttpStatus.BAD_REQUEST);
        }else {
            Orders order2 = cartRepository.findById(cartId).map(cart -> {
                orders.setCart(cart);
                return orderRepository.save(orders);
            }).orElseThrow(() -> new ApiRequestException("Cart by " + cartId + " not Found", HttpStatus.NOT_FOUND));
            return orderRepository.save(order2);
        }
    }


    public Orders editOrder(Orders order, final Integer orderId){
        Orders order2 = getOrderById(orderId);

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
        Orders o = getOrderById(orderId);
        if(o == null){
            throw new ApiRequestException("Order by ID " + orderId + " not found", HttpStatus.NOT_FOUND);
        }
        orderRepository.deleteById(orderId);
        throw new ApiRequestException("Order by ID " + orderId + " successfully deleted", HttpStatus.OK);
    }


}
