package com.ex.caffeine_bliss.controllers;

import com.ex.caffeine_bliss.DTOs.request.RequestSaveOrderDTO;
import com.ex.caffeine_bliss.services.OrderService;
import com.ex.caffeine_bliss.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(path = "/add")
    public ResponseEntity<StandardResponse> addOrder(@RequestBody RequestSaveOrderDTO saveOrderDTO){
        String message = orderService.addOrder(saveOrderDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "SUCCESS",
                        message), HttpStatus.CREATED);
    }
}
