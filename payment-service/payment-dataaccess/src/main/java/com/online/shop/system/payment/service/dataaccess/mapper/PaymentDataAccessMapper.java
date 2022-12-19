package com.online.shop.system.payment.service.dataaccess.mapper;

import com.online.shop.system.payment.service.dataaccess.entity.PaymentEntity;
import com.online.shop.system.payment.service.dataaccess.entity.UserEntity;
import com.online.shop.system.payment.service.domain.entity.Payment;
import com.online.shop.system.payment.service.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class PaymentDataAccessMapper {

    public PaymentEntity paymentToPaymentEntity(Payment payment){
        return PaymentEntity.builder()
                .id(payment.getId())
                .orderID(payment.getOrderID())
                .user(UserEntity.builder()
                        .id(payment.getUserID())
                        .build())
                .price(payment.getPrice())
                .purchasedDate(payment.getPurchaseDate())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }

    public Payment paymentEntityToPayment(PaymentEntity paymentEntity){
        return Payment.builder()
                .id(paymentEntity.getId())
                .userID(paymentEntity.getUser().getId())
                .orderID(paymentEntity.getOrderID())
                .price(paymentEntity.getPrice())
                .paymentStatus(paymentEntity.getPaymentStatus())
                .purchaseDate(paymentEntity.getPurchasedDate())
                .build();
    }

    public UserEntity userToUserEntity(User user){
        return UserEntity.builder()
                .id(user.getId())
                .build();
    }
}
