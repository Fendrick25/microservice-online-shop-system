package com.online.shop.system.payment.service.application.rest;

import com.online.shop.system.payment.service.domain.dto.request.PayOrder;
import com.online.shop.system.payment.service.domain.dto.request.response.Data;
import com.online.shop.system.payment.service.domain.dto.request.response.PayOrderResponse;
import com.online.shop.system.payment.service.domain.ports.input.service.PaymentApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentApplicationService paymentApplicationService;

    @PostMapping
    public ResponseEntity<Data<PayOrderResponse>> payOrder(@RequestBody PayOrder payOrder){
        return new ResponseEntity<>(new Data<>(paymentApplicationService.payOrder(payOrder)), HttpStatus.OK);
    }
}
