package com.semester3.davines.controller;

import com.semester3.davines.domain.models.Order;
import com.semester3.davines.domain.requests.CreateOrderRequest;
import com.semester3.davines.domain.requests.UpdateOrderStatus;
import com.semester3.davines.domain.response.CreateOrderResponse;
import com.semester3.davines.domain.response.GetAllOrdersResponse;
import com.semester3.davines.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/orders")
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public Page<Order> getAllOrders(@RequestParam("orderPage") int orderPage) {
        return orderService.getAllOrders(orderPage);
    }

    @PostMapping("/create")
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request);
    }

    //method to get the count of the pages

    //method to get all orders of specific user based on email using pagination
    @GetMapping("/{id}")
    public Page<Order> getAllOrdersByEmail(@RequestParam(name = "orderPage") int orderPage, @PathVariable Long id) {
        return orderService.getAllOrdersByUserEmail(orderPage, id);
    }

    //method to get the last 3 orders of a specific user based on email
    @GetMapping("/user/{id}")
    public GetAllOrdersResponse getLastThreeOrders(@PathVariable Long id) {
        return orderService.getLastThreeOrders(id);
    }

    //method to update status of the order and change quantity of products if it's in IN_PROGRESS based on the order
    @IsAuthenticated
    @RolesAllowed("ROLE_ADMIN")
    @PutMapping("/update/{id}")
    public void updateStatusAndQuantity(@RequestBody UpdateOrderStatus request, @PathVariable Long id) {
        orderService.updateProductQuantity(request, id);
    }
    //method to get the details of an order based on id and check in service based on token if the use is admin
    //if the user is admin he can see the details of every order if he is not admin he can see only his orders
    //from token you can take id and get his email so that you can check if the order is from the logged-in user
    //vij UserServiceImpl line 74 ili 83 if statement
}
