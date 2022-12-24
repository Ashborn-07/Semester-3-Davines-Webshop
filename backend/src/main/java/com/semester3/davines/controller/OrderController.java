package com.semester3.davines.controller;

import com.semester3.davines.domain.response.GetAllOrdersResponse;
import com.semester3.davines.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<GetAllOrdersResponse> getAllOrders() {
        GetAllOrdersResponse response = orderService.getAllOrders();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
