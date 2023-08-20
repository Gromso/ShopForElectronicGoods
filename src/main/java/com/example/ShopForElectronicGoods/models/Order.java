package com.example.ShopForElectronicGoods.models;
import com.example.ShopForElectronicGoods.models.ENUMS.OrderStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name ="order")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Integer order_id;

    @Basic(optional = false)
    @Column(name = "created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at_order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonIgnore
    private Cart cart;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatusEnum status = OrderStatusEnum.pending;

}
