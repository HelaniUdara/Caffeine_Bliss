package com.ex.caffeine_bliss.services.impl;

import com.ex.caffeine_bliss.DTOs.request.RequestSaveOrderDTO;
import com.ex.caffeine_bliss.entities.Order;
import com.ex.caffeine_bliss.entities.OrderItems;
import com.ex.caffeine_bliss.repositories.*;
import com.ex.caffeine_bliss.services.OrderService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public String addOrder(RequestSaveOrderDTO saveOrderDTO) {
        Order order = new Order(
                customerRepository.getReferenceById(saveOrderDTO.getCustomer()),
                userRepository.getReferenceById(saveOrderDTO.getCashier()),
                saveOrderDTO.getTotalPrice(),
                saveOrderDTO.getPaymentMethod()
        );
        orderRepository.save(order);

        if (orderRepository.existsById(order.getId())) {
            List<OrderItems> orderItems = modelMapper.map(saveOrderDTO.getOrderItems(), new TypeToken<List<OrderItems>>() {
            }.getType());
            for (int i = 0; i < orderItems.size(); i++) {
                orderItems.get(i).setOrder(order);
                orderItems.get(i).setProduct(productRepository.getReferenceById(saveOrderDTO.getOrderItems().get(i)
                        .getProduct()));
            }
            if (orderItems.size() > 0) {
                orderItemRepository.saveAll(orderItems);
            }
            return "Order Saved.";
        }
        return "Order Failed";
    }
}
