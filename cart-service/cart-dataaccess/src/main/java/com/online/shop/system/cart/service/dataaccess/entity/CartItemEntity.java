package com.online.shop.system.cart.service.dataaccess.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cart_items")
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private UUID productID;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private CartEntity cart;
}
