package com.online.shop.system.payment.service.domain;

import com.online.shop.system.payment.service.domain.dto.message.CancelPayment;
import com.online.shop.system.payment.service.domain.dto.message.PaymentRequest;
import com.online.shop.system.payment.service.domain.dto.request.PayOrder;
import com.online.shop.system.payment.service.domain.dto.request.response.PayOrderResponse;
import com.online.shop.system.payment.service.domain.entity.Payment;
import com.online.shop.system.payment.service.domain.event.PaymentCancelledEvent;
import com.online.shop.system.payment.service.domain.event.PaymentCreatedEvent;
import com.online.shop.system.payment.service.domain.event.PaymentExpiredEvent;
import com.online.shop.system.payment.service.domain.event.PaymentPaidEvent;
import com.online.shop.system.payment.service.domain.mapper.PaymentDataMapper;
import com.online.shop.system.payment.service.domain.ports.input.service.PaymentApplicationService;
import com.online.shop.system.payment.service.domain.ports.output.message.publisher.OrderMessagePublisher;
import com.online.shop.system.payment.service.domain.ports.output.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
@Slf4j
@RequiredArgsConstructor
public class PaymentApplicationServiceImpl implements PaymentApplicationService {

    private final PaymentDataMapper paymentDataMapper;
    private final PaymentDomainService paymentDomainService;
    private final PaymentRepository paymentRepository;
    private final OrderMessagePublisher orderMessagePublisher;


    @Override
    public PayOrderResponse payOrder(PayOrder payOrder) {
        Payment payment = paymentRepository.getPayment(payOrder.getPaymentID());
        PaymentPaidEvent paymentPaidEvent = paymentDomainService.payPayment(payment, payOrder.getPrice());
        paymentRepository.updatePayment(payment);
        orderMessagePublisher.paymentPaid(paymentPaidEvent);
        log.info("Payment with paymentID: {}, orderID: {} paid", payment.getId(), payment.getOrderID());
        return paymentDataMapper.paymentToPayOrderResponse(payment);
    }


    @Override
    public void paymentExpired(Payment payment) {
        PaymentExpiredEvent paymentExpiredEvent = paymentDomainService.expirePayment(payment);
        paymentRepository.updatePayment(payment);
        orderMessagePublisher.paymentExpired(paymentExpiredEvent);
        log.info("Payment with paymentID: {}, orderID: {} expired", payment.getId(), payment.getOrderID());

    }
}
