package com.online.shop.system.payment.service.dataaccess.adapter;

import com.online.shop.system.payment.service.dataaccess.entity.PaymentEntity;
import com.online.shop.system.payment.service.dataaccess.exception.ResourceNotFoundException;
import com.online.shop.system.payment.service.dataaccess.mapper.PaymentDataAccessMapper;
import com.online.shop.system.payment.service.dataaccess.repository.PaymentJpaRepository;
import com.online.shop.system.payment.service.domain.entity.Payment;
import com.online.shop.system.payment.service.domain.ports.output.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentDataAccessMapper paymentDataAccessMapper;
    private final PaymentJpaRepository paymentJpaRepository;


    @Override
    public Payment createPayment(Payment payment) {
        return paymentDataAccessMapper.paymentEntityToPayment(paymentJpaRepository.save(paymentDataAccessMapper.paymentToPaymentEntity(payment)));
    }

    @Override
    public Payment getPayment(UUID paymentID) {
        return paymentDataAccessMapper.paymentEntityToPayment(findPayment(paymentID));
    }

    @Override
    @Transactional
    public void updatePayment(Payment payment) {
        PaymentEntity paymentEntity = findPayment(payment.getId());
        paymentEntity.setPaymentStatus(payment.getPaymentStatus());
    }

    @Override
    public Payment getPaymentByOrderID(UUID orderID) {
        return paymentDataAccessMapper.paymentEntityToPayment(paymentJpaRepository.findByOrderID(orderID));
    }

    private PaymentEntity findPayment(UUID paymentID){
        return paymentJpaRepository.findById(paymentID).orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
    }

}
