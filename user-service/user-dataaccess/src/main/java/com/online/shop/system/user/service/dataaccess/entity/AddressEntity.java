package com.online.shop.system.user.service.dataaccess.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer addressID;
    private String street;
    private String postalCode;
    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;
}
