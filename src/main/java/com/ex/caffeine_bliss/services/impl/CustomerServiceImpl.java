package com.ex.caffeine_bliss.services.impl;

import com.ex.caffeine_bliss.DTOs.CustomerDTO;
import com.ex.caffeine_bliss.DTOs.request.RequestAddCustomerDTO;
import com.ex.caffeine_bliss.entities.Customer;
import com.ex.caffeine_bliss.entities.Order;
import com.ex.caffeine_bliss.repositories.CustomerRepository;
import com.ex.caffeine_bliss.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CustomerDTO findByMobileNumber(String mobileNo) {
        Customer customer = customerRepository.findByMobileNo(mobileNo);
        if(customer != null){
            return modelMapper.map(customer, CustomerDTO.class);
        }
        return null;
    }

    @Override
    public CustomerDTO findByName(String name) {
        Customer customer = customerRepository.findByName(name);
        if(customer != null){
            return modelMapper.map(customer, CustomerDTO.class);
        }
        return null;
    }

    @Override
    public String createCustomer(RequestAddCustomerDTO customerDTO) {

        if(customerRepository.existsByMobileNo(customerDTO.getMobileNo())){
            return "Customer already there!";
        }else{
            Customer customer = modelMapper.map(customerDTO, Customer.class);
            customerRepository.save(customer);
            return "Added customer " + customerDTO.getName();
        }
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO dto) {
        return null;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return null;
    }

    @Override
    public List<CustomerDTO> getCustomersWithOverXOrders(int x) {
        return null;
    }

    @Override
    public List<Order> getCustomerOrderHistory(UUID customerId) {
        return null;
    }
}
