package com.ex.caffeine_bliss.services.impl;

import com.ex.caffeine_bliss.DTOs.CustomerDTO;
import com.ex.caffeine_bliss.DTOs.paginated.PaginatedResponse;
import com.ex.caffeine_bliss.DTOs.request.RequestAddCustomerDTO;
import com.ex.caffeine_bliss.DTOs.request.RequestUpdateCustomerDTO;
import com.ex.caffeine_bliss.entities.Customer;
import com.ex.caffeine_bliss.exceptions.DuplicateElementException;
import com.ex.caffeine_bliss.exceptions.ResourceNotFoundException;
import com.ex.caffeine_bliss.repositories.CustomerRepository;
import com.ex.caffeine_bliss.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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
        }else {
            throw new ResourceNotFoundException("This mobile number does not exists");
        }
    }

    @Override
    public CustomerDTO findByName(String name) {
        Customer customer = customerRepository.findByName(name);
        if(customer != null){
            return modelMapper.map(customer, CustomerDTO.class);
        }else {
            throw new ResourceNotFoundException("This name does not exists");
        }
    }

    @Override
    public String createCustomer(RequestAddCustomerDTO customerDTO) {

        if(customerRepository.existsByMobileNo(customerDTO.getMobileNo())){
            throw new DuplicateElementException("Mobile number already registered!");
        }else{
            Customer customer = modelMapper.map(customerDTO, Customer.class);
            customerRepository.save(customer);
            return "Added customer " + customerDTO.getName();
        }
    }

    @Override
    public CustomerDTO updateCustomer(RequestUpdateCustomerDTO dto) {
        if(customerRepository.existsById(dto.getId())){
            Customer customer = customerRepository.getReferenceById(dto.getId());
            customer.setName(dto.getName());
            customer.setEmail(dto.getEmail());
            customer.setMobileNo(dto.getMobileNo());
            customerRepository.save(customer);
            return modelMapper.map(customer, CustomerDTO.class);
        }else {
            throw new ResourceNotFoundException("Record not exists by ID: " + dto.getId());
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        if(customers.size() < 1){
            throw new ResourceNotFoundException("There aren't customers according to the request.");
        }else{
            List<CustomerDTO> customerDTOList = modelMapper.map(customers, new TypeToken<List<CustomerDTO>>(){}.getType());
            return customerDTOList;
        }
    }

    @Override
    public List<CustomerDTO> getCustomersWithOverXOrders(int x) {
        return null;
    }

    @Override
    public PaginatedResponse<CustomerDTO> getLimitedCustomers(int page, int limit) {
        Page<Customer> customers = customerRepository.findAll(PageRequest.of(page, limit));
        if (customers.getSize() < 1) {
            throw new ResourceNotFoundException("There aren't customers according to the request.");
        } else {
            List<Customer> customerEntities = customers.getContent();
            List<CustomerDTO> customerList = modelMapper.map(customerEntities, new TypeToken<List<CustomerDTO>>() {
            }.getType());
            int totalCount = (int) customerRepository.count();
            return new PaginatedResponse<CustomerDTO>(customerList, page, limit, totalCount);
        }
    }
}
