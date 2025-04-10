package com.ex.caffeine_bliss.controllers;

import com.ex.caffeine_bliss.DTOs.CustomerDTO;
import com.ex.caffeine_bliss.DTOs.paginated.PaginatedResponse;
import com.ex.caffeine_bliss.DTOs.request.RequestAddCustomerDTO;
import com.ex.caffeine_bliss.DTOs.request.RequestUpdateCustomerDTO;
import com.ex.caffeine_bliss.services.CustomerService;
import com.ex.caffeine_bliss.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(path = "/getByMobile", params = "mobileNo")
    public ResponseEntity<StandardResponse> getCustomerByMobile(@RequestParam(value = "mobileNo") String mobileNo) {
        CustomerDTO customerDTO = customerService.findByMobileNumber(mobileNo);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        customerDTO), HttpStatus.OK);
    }

    @GetMapping(path = "/getByName", params = "name")
    public ResponseEntity<StandardResponse> getCustomerByName(@RequestParam(value = "name") String name){
        CustomerDTO customerDTO = customerService.findByName(name);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        customerDTO), HttpStatus.OK);
    }

    @GetMapping(path = "/getAllCustomers")
    public ResponseEntity<StandardResponse> getAllCustomers(){
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        customers), HttpStatus.OK);
    }

    @GetMapping(path = "/getLimitedCustomers", params = {"page", "limit"})
    public ResponseEntity<StandardResponse> getLimitedCustomers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit
            ){
        if(limit > 20){ limit = 20; }
        PaginatedResponse<CustomerDTO> customerList = customerService.getLimitedCustomers(page,limit);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        customerList), HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<StandardResponse> createCustomer(@RequestBody RequestAddCustomerDTO customerDTO){
        String message = customerService.createCustomer(customerDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "SUCCESS",
                        message), HttpStatus.CREATED);
    }

    @PostMapping(path = "/update")
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody RequestUpdateCustomerDTO customerDTO){
        CustomerDTO customer = customerService.updateCustomer(customerDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        customer), HttpStatus.OK);
    }
}
