package com.online.shop.system.cart.service.dataaccess.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "carts")
public class CartEntity {

    @Id
    private UUID id;
    @Column(unique = true)
    private UUID userID;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartItemEntity> cartItems;
}
