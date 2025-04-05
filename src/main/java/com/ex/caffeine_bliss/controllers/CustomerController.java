package com.ex.caffeine_bliss.controllers;

import com.ex.caffeine_bliss.DTOs.CustomerDTO;
import com.ex.caffeine_bliss.DTOs.request.RequestAddCustomerDTO;
import com.ex.caffeine_bliss.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(path = "/getByMobile", params = "mobileNo")
    public CustomerDTO getCustomerByMobile(@RequestParam(value = "mobileNo") String mobileNo){
        return customerService.findByMobileNumber(mobileNo);
    }

    @GetMapping(path = "/getByName", params = "name")
    public CustomerDTO getCustomerByName(@RequestParam(value = "name") String name){
        return customerService.findByName(name);
    }

    @PostMapping(path = "/add")
    public String createCustomer(@RequestBody RequestAddCustomerDTO customerDTO){
        return customerService.createCustomer(customerDTO);
    }
}
