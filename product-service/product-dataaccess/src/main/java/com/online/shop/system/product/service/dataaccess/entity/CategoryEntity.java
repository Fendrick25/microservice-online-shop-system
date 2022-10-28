package com.online.shop.system.product.service.dataaccess.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private UUID categoryID;
    private String name;
}
