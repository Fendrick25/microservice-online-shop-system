package com.online.shop.system.order.service.application.rest;

import com.online.shop.system.order.service.domain.dto.create.CreateOrder;
import com.online.shop.system.order.service.domain.dto.create.GetOrderByDate;
import com.online.shop.system.order.service.domain.dto.create.GetOrderByStatus;
import com.online.shop.system.order.service.domain.dto.create.response.CreateOrderResponse;
import com.online.shop.system.order.service.domain.dto.create.response.Data;
import com.online.shop.system.order.service.domain.dto.create.response.GetOrderResponse;
import com.online.shop.system.order.service.domain.dto.create.response.PagingResponse;
import com.online.shop.system.order.service.domain.dto.message.UpdateOrderDetail;
import com.online.shop.system.order.service.domain.ports.input.service.OrderApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderApplicationService orderApplicationService;

    @PostMapping
    public ResponseEntity<Data<CreateOrderResponse>> createOrder(@RequestBody CreateOrder createOrder){
        return new ResponseEntity<>(new Data<>(orderApplicationService.requestOrder(createOrder)), HttpStatus.CREATED);
    }


    @GetMapping("/{orderID}")
    public ResponseEntity<Data<GetOrderResponse>> getOrder(@PathVariable("orderID")UUID orderID){
        return new ResponseEntity<>(new Data<>(orderApplicationService.getOrder(orderID)), HttpStatus.OK);
    }

    @PostMapping("/finish")
    public ResponseEntity<?> finishOrder(@RequestBody UpdateOrderDetail updateOrderDetail){
        orderApplicationService.orderFinished(updateOrderDetail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancelOrder(@RequestBody UpdateOrderDetail updateOrderDetail){
        orderApplicationService.orderCancelled(updateOrderDetail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userID}")
    public ResponseEntity<PagingResponse> getAllOrder(@PathVariable("userID") UUID userID,
                                                      @RequestParam(defaultValue = "1") int page,
                                                      @RequestParam(defaultValue = "10") int size){
        return new ResponseEntity<>(orderApplicationService.getAllOrder(userID, page, size), HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<PagingResponse> getOrderByDate(@RequestBody GetOrderByDate getOrderByDate,
                                                         @RequestParam(defaultValue = "1") int page,
                                                         @RequestParam(defaultValue = "10") int size){
        return new ResponseEntity<>(orderApplicationService.getOrderByDate(getOrderByDate, page, size), HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<PagingResponse> getOrderByDate(@RequestBody GetOrderByStatus getOrderByStatus,
                                                         @RequestParam(defaultValue = "1") int page,
                                                         @RequestParam(defaultValue = "10") int size){
        return new ResponseEntity<>(orderApplicationService.getOrderByStatus(getOrderByStatus, page, size), HttpStatus.OK);
    }

}
