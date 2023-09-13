package com.example.ShopForElectronicGoods.services.CartServices;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.*;
import com.example.ShopForElectronicGoods.modelsDTO.ArticleDTO.ArticleDTO;
import com.example.ShopForElectronicGoods.modelsDTO.ArticleDTO.ArticlePriceDTO;
import com.example.ShopForElectronicGoods.modelsDTO.Cart.CartArticleDTO;
import com.example.ShopForElectronicGoods.modelsDTO.Cart.CartResponseDTO;
import com.example.ShopForElectronicGoods.modelsDTO.LoginResponseDTO;
import com.example.ShopForElectronicGoods.modelsDTO.Orders.OrdersResponseDTO;
import com.example.ShopForElectronicGoods.modelsDTO.RegistrationDTO;
import com.example.ShopForElectronicGoods.repository.ArticlePriceRepository;
import com.example.ShopForElectronicGoods.repository.ArticleRepository;
import com.example.ShopForElectronicGoods.repository.CartArticleRepository;
import com.example.ShopForElectronicGoods.repository.OrderRepository;
import com.example.ShopForElectronicGoods.services.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceForMethod {

    @Autowired
    private CartArticleRepository cartArticleRepository;

    @Autowired
    private OrderRepository ordersRepository;

    @Autowired
    private CartServicesForActiveCart cartServicesForActiveCart;

    @Autowired
    private CartService cartService;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticlePriceRepository articlePriceRepository;

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;



    public Orders getOrderByCartId(Integer cartId){
        List<Orders> orders = ordersRepository.getOrderByCartId(cartId);
        if(orders.isEmpty()){
            return null;
        }else{
            return orders.get(0);
        }
    }


    public CartResponseDTO getActiveCartForUserId(Long userId){
        Cart userCart = cartServicesForActiveCart.getLastActiveCartByUserId(Math.toIntExact(userId));
        Orders orderCart = null;
        if(userCart != null ) {
            orderCart = getOrderByCartId(userCart.getCart_id());
        }
        if(userCart == null || orderCart != null){
            userCart =  cartService.createNewCartForUser(Math.toIntExact(userId));
        }
        ApplicationUser user = userService.findUserById(Math.toIntExact(userId));
        RegistrationDTO user2 = new RegistrationDTO();
        user2.setEmail(user.getEmail());
        user2.setForename(user.getForename());
        user2.setSurname(user.getSurname());
        user2.setPhone_number(user.getPhone_number());
        user2.setPostal_address(user.getPostal_address());

        List<CartArticle> cartArticles = cartArticleRepository.findCartArticleByCartId(userCart.getCart_id());

        List<CartArticleDTO> cartArticleDTOs = cartArticles.stream()
                .map(cartArticle -> {
                    CartArticleDTO dto = new CartArticleDTO();
                    dto.setCartArticle_id(cartArticle.getCart_article_id());
                    dto.setCart_id(cartArticle.getCart().getCart_id());
                    dto.setArticle_id(cartArticle.getArticle().getArticle_id());
                    dto.setQuantity(cartArticle.getQuantity());
                    dto.setArticles(getArticleDTO(cartArticle.getArticle().getArticle_id(),cartArticle.getCart_article_id()));
                    return dto;
                })
                .collect(Collectors.toList());

        // Kreiranje odgovarajućeg JSON odgovora
        CartResponseDTO responseDTO = new CartResponseDTO();
        responseDTO.setCart_id(userCart.getCart_id());
        responseDTO.setUser_id(Math.toIntExact(userId));
        responseDTO.setUser(user2);
        responseDTO.setCreated_at_cart(userCart.getCreated_at_cart());
        responseDTO.setCartArticles(cartArticleDTOs);

        return responseDTO;

    }

    public ArticleDTO getArticleDTO(Integer articleId, Integer cartArticleId){
        Article a = articleRepository.findArticleByArticleIdAndCartArticleId(articleId,cartArticleId);
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setArticle_id(a.getArticle_id());
        articleDTO.setName(a.getName());
        articleDTO.setExcerpt(a.getExcerpt());
        articleDTO.setDescription(a.getDescription());
        articleDTO.setStatus(a.getStatus());
        articleDTO.setIsPromoted(a.getIs_promoted());
        articleDTO.setCategory(a.getCategory());

        List<ArticlePrice> app = articlePriceRepository.findAll();
        List<ArticlePriceDTO> ap = app.stream().map(art ->{
            ArticlePriceDTO artDTO = new ArticlePriceDTO();
            artDTO.setArticlePrice_id(art.getArticle_price_id());
            artDTO.setArticle_id(art.getArticle().getArticle_id());
            artDTO.setPrice(art.getPrice());
            artDTO.setCreated_at_price(art.getCreated_at_price());
            return artDTO;
        }).collect(Collectors.toList());
        articleDTO.setArticlePrices(ap);
        return articleDTO;
    }

    public CartResponseDTO getListCartArticles(Integer cartId, Integer userId){
        Cart cart = cartService.getCartByCartId(cartId);
        ApplicationUser user = userService.findUserById(userId);
        RegistrationDTO user2 = new RegistrationDTO();
        user2.setEmail(user.getEmail());
        user2.setForename(user.getForename());
        user2.setSurname(user.getSurname());
        user2.setPhone_number(user.getPhone_number());
        user2.setPostal_address(user.getPostal_address());


        List<CartArticle> cartArticles = cartArticleRepository.findCartArticleByCartId(cartId);

        if (cartArticles.isEmpty()) {
            throw new ApiRequestException("cartArticle is Empty", HttpStatus.NOT_FOUND);
        }

        // Mapiranje na DTO objekte
        List<CartArticleDTO> cartArticleDTOs = cartArticles.stream()
                .map(cartArticle -> {
                    CartArticleDTO dto = new CartArticleDTO();
                    dto.setCartArticle_id(cartArticle.getCart_article_id());
                    dto.setCart_id(cartArticle.getCart().getCart_id());
                    dto.setArticle_id(cartArticle.getArticle().getArticle_id());
                    dto.setQuantity(cartArticle.getQuantity());
                    dto.setArticles(getArticleDTO(cartArticle.getArticle().getArticle_id(),cartArticle.getCart_article_id()));
                    return dto;
                })
                .collect(Collectors.toList());

        // Kreiranje odgovarajućeg JSON odgovora
        CartResponseDTO responseDTO = new CartResponseDTO();
        responseDTO.setCart_id(cart.getCart_id());
        responseDTO.setUser_id(userId);
        responseDTO.setUser(user2);
        responseDTO.setCreated_at_cart(cart.getCreated_at_cart());
        responseDTO.setCartArticles(cartArticleDTOs);

        return responseDTO;
    }

    public OrdersResponseDTO getOrdersResponse(Integer cartId, Integer userId, Integer orderId) {
        CartResponseDTO cart = getListCartArticles(cartId, userId);
        Orders orders = ordersRepository.findById(orderId).orElseThrow(() -> new ApiRequestException("order by order ID " + orderId + " not found"));

        OrdersResponseDTO ordersResponseDTO = new OrdersResponseDTO();
        ordersResponseDTO.setOrder_id(orders.getOrder_id());
        ordersResponseDTO.setCreated_at_order(orders.getCreated_at_order());
        ordersResponseDTO.setStatus(orders.getStatus());
        ordersResponseDTO.setCart(cart);
        return ordersResponseDTO;

    }

}
