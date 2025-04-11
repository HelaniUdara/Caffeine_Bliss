package com.ex.caffeine_bliss.controllers;

import com.ex.caffeine_bliss.DTOs.paginated.PaginatedResponse;
import com.ex.caffeine_bliss.DTOs.request.RequestSaveOrderDTO;
import com.ex.caffeine_bliss.DTOs.response.ResponseOrderDetailsDTO;
import com.ex.caffeine_bliss.DTOs.response.ResponseOrderSummaryDTO;
import com.ex.caffeine_bliss.services.OrderService;
import com.ex.caffeine_bliss.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/getOrdersByCustomer", params = {"customerId", "page", "limit"})
    public ResponseEntity<StandardResponse> getOrdersByCustomer(
            @RequestParam(value = "customerId") UUID customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ){
        if(limit > 20){ limit = 20; }
        PaginatedResponse<ResponseOrderSummaryDTO> orders = orderService.getOrdersByCustomerId(customerId, page, limit);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        orders), HttpStatus.OK);
    }

    @GetMapping(value = "/getOrdersByCashier", params = {"cashierId", "page", "limit"})
    public ResponseEntity<StandardResponse> getOrdersByCashier(
            @RequestParam(value = "cashierId") UUID cashierId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ){
        if(limit > 20){ limit = 20; }
        PaginatedResponse<ResponseOrderSummaryDTO> orders = orderService.getOrdersByCashierId(cashierId, page, limit);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        orders), HttpStatus.OK);
    }

    @GetMapping(value = "/getOrderDetails", params = "id")
    public ResponseEntity<StandardResponse> getOrderDetailsById(@RequestParam(value = "id") UUID id){
        ResponseOrderDetailsDTO orderDetails = orderService.getOrdersDetailsById(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        orderDetails), HttpStatus.OK);
    }

    @GetMapping(value = "/getAllOrders", params = {"page", "limit"})
    public ResponseEntity<StandardResponse> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ){
        if(limit > 20){ limit = 20; }
        PaginatedResponse<ResponseOrderSummaryDTO> orders = orderService.getAllOrders(page, limit);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        orders), HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<StandardResponse> addOrder(@RequestBody RequestSaveOrderDTO saveOrderDTO){
        String message = orderService.addOrder(saveOrderDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "SUCCESS",
                        message), HttpStatus.CREATED);
    }
}
