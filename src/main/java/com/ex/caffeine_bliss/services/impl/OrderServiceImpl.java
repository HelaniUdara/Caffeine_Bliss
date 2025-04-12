package com.ex.caffeine_bliss.services.impl;

import com.ex.caffeine_bliss.DTOs.paginated.PaginatedResponse;
import com.ex.caffeine_bliss.DTOs.quesryInterfaces.OrderDetailInterface;
import com.ex.caffeine_bliss.DTOs.quesryInterfaces.OrderItemDetailInterface;
import com.ex.caffeine_bliss.DTOs.quesryInterfaces.OrderSummaryInterface;
import com.ex.caffeine_bliss.DTOs.request.RequestSaveOrderDTO;
import com.ex.caffeine_bliss.DTOs.response.ResponseOrderDetailsDTO;
import com.ex.caffeine_bliss.DTOs.response.ResponseOrderItemDTO;
import com.ex.caffeine_bliss.DTOs.response.ResponseOrderSummaryDTO;
import com.ex.caffeine_bliss.entities.Order;
import com.ex.caffeine_bliss.entities.OrderItems;
import com.ex.caffeine_bliss.exceptions.ResourceNotFoundException;
import com.ex.caffeine_bliss.repositories.*;
import com.ex.caffeine_bliss.services.OrderService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Override
    public PaginatedResponse<ResponseOrderSummaryDTO> getOrdersByCustomerId(UUID customerId, int page, int limit) {
        List<OrderSummaryInterface> orderSummaryInterfaces = orderRepository.getAllOrderSummariesByCustomer(
                customerId, PageRequest.of(page, limit));
        if(orderSummaryInterfaces.size() < 1){
            throw new ResourceNotFoundException("There aren't orders according to the request.");
        }else{
            List<ResponseOrderSummaryDTO> orderList = new ArrayList<>();
            for (OrderSummaryInterface o : orderSummaryInterfaces) {
                orderList.add(
                        new ResponseOrderSummaryDTO(UUID.fromString(o.getId()), o.getCreatedAt(), o.getPaymentMethod(),
                                o.getTotalPrice(), o.getCustomerName(), o.getCashierName())
                );
            }
            int totalCount = orderRepository.countAllOrderSummariesByCustomer(customerId);
            return new PaginatedResponse<ResponseOrderSummaryDTO>(orderList, page, limit, totalCount);
        }
    }

    @Override
    public PaginatedResponse<ResponseOrderSummaryDTO> getOrdersByCashierId(UUID cashierId, int page, int limit) {
        List<OrderSummaryInterface> orderSummaryInterfaces = orderRepository.getAllOrderSummariesByCashier(
                cashierId, PageRequest.of(page, limit));
        if(orderSummaryInterfaces.size() < 1){
            throw new ResourceNotFoundException("There aren't orders according to the request.");
        }else{
            List<ResponseOrderSummaryDTO> orderList = new ArrayList<>();
            for (OrderSummaryInterface o : orderSummaryInterfaces) {
                orderList.add(
                        new ResponseOrderSummaryDTO(UUID.fromString(o.getId()), o.getCreatedAt(), o.getPaymentMethod(),
                                o.getTotalPrice(), o.getCustomerName(), o.getCashierName())
                );
            }
            int totalCount = orderRepository.countAllOrderSummariesByCashier(cashierId);
            return new PaginatedResponse<ResponseOrderSummaryDTO>(orderList, page, limit, totalCount);
        }
    }

    @Override
    public PaginatedResponse<ResponseOrderSummaryDTO> getAllOrders(int page, int limit) {
        List<OrderSummaryInterface> orderSummaryInterfaces = orderRepository.getAllOrders(PageRequest.of(page, limit));
        if(orderSummaryInterfaces.size() < 1){
            throw new ResourceNotFoundException("There aren't orders according to the request.");
        }else{
            List<ResponseOrderSummaryDTO> orderList = new ArrayList<>();
            for (OrderSummaryInterface o : orderSummaryInterfaces) {
                orderList.add(
                        new ResponseOrderSummaryDTO(UUID.fromString(o.getId()), o.getCreatedAt(), o.getPaymentMethod(),
                                o.getTotalPrice(), o.getCustomerName(), o.getCashierName())
                );
            }
            int totalCount = (int) orderRepository.count();
            return new PaginatedResponse<ResponseOrderSummaryDTO>(orderList, page, limit, totalCount);
        }
    }

    @Override
    public ResponseOrderDetailsDTO getOrdersDetailsById(UUID id) {
        OrderDetailInterface orderDetails = orderRepository.getOrderDetailsByID(id);
        if (orderDetails == null) {
            throw new ResourceNotFoundException("Cannot find details for the order ID: " + id);
        } else {
            List<OrderItemDetailInterface> items = orderRepository.getOrderItemsByOrderId(id);
            if (items.size() < 1) {
                throw new ResourceNotFoundException("There is not any item in the order.");
            } else {
                List<ResponseOrderItemDTO> orderItemsList = new ArrayList<>();
                for (OrderItemDetailInterface o : items) {
                    orderItemsList.add(
                            new ResponseOrderItemDTO(o.getProductName(), o.getUnitPrice(), o.getQuantity(), o.getAmount())
                    );
                }
                return new ResponseOrderDetailsDTO(
                        orderDetails.getOrderId(), orderDetails.getCreatedAt(), orderDetails.getCustomerName(),
                        orderDetails.getCustomerMobile(), orderDetails.getCustomerEmail(), orderDetails.getTotalPrice(), orderItemsList
                );
            }
        }
    }
}
