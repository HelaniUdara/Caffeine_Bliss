package com.ex.caffeine_bliss.services;

import com.ex.caffeine_bliss.DTOs.CustomerDTO;
import com.ex.caffeine_bliss.DTOs.request.RequestAddCustomerDTO;
import com.ex.caffeine_bliss.entities.Order;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    CustomerDTO findByMobileNumber(String mobileNo);
    CustomerDTO findByName(String name);
    String createCustomer(RequestAddCustomerDTO dto);
    CustomerDTO updateCustomer(CustomerDTO dto);
    List<CustomerDTO> getAllCustomers();
    List<CustomerDTO> getCustomersWithOverXOrders(int x);
    List<Order> getCustomerOrderHistory(UUID customerId);
}
