package com.online.shop.system.product.service.dataaccess.entity;


import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
@Entity
public class CategoryEntity {

    @Id
    private UUID id;
    private String name;
}
