package com.online.shop.system.payment.service.domain;

import com.online.shop.system.payment.service.domain.dto.message.CancelPayment;
import com.online.shop.system.payment.service.domain.dto.message.PaymentRequest;
import com.online.shop.system.payment.service.domain.entity.Payment;
import com.online.shop.system.payment.service.domain.event.PaymentCancelledEvent;
import com.online.shop.system.payment.service.domain.mapper.PaymentDataMapper;
import com.online.shop.system.payment.service.domain.ports.input.message.listener.OrderMessageListener;
import com.online.shop.system.payment.service.domain.ports.input.service.PaymentApplicationService;
import com.online.shop.system.payment.service.domain.ports.output.message.publisher.OrderMessagePublisher;
import com.online.shop.system.payment.service.domain.ports.output.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Slf4j
@RequiredArgsConstructor
@Validated
public class OrderMessageListenerImpl implements OrderMessageListener {

    private final PaymentDomainService paymentDomainService;
    private final PaymentRepository paymentRepository;
    private final PaymentDataMapper paymentDataMapper;
    private final OrderMessagePublisher orderMessagePublisher;

    @Override
    public void createPayment(PaymentRequest paymentRequest) {
        Payment payment = paymentDataMapper.paymentRequestToPayment(paymentRequest);
        paymentDomainService.initializePayment(payment);
        Payment savedPayment = paymentRepository.createPayment(payment);
        orderMessagePublisher.paymentCreated(paymentDataMapper.paymentToPaymentCreatedEvent(savedPayment));
        log.info("Payment with paymentID: {}, orderID: {} created", savedPayment.getId(), savedPayment.getOrderID());

    }

    @Override
    public void cancelPayment(CancelPayment cancelPayment) {
        Payment payment = paymentRepository.getPaymentByOrderID(cancelPayment.getOrderID());
        PaymentCancelledEvent paymentCancelledEvent = paymentDomainService.cancelPayment(payment);
        paymentRepository.updatePayment(payment);
        orderMessagePublisher.paymentCancelled(paymentCancelledEvent);
        log.info("Payment with paymentID: {}, orderID: {} cancelled", payment.getId(), payment.getOrderID());
    }
}
