package com.ex.caffeine_bliss.services.impl;

import com.ex.caffeine_bliss.DTOs.ReceiptDTO;
import com.ex.caffeine_bliss.DTOs.ReceiptEmailDTO;
import com.ex.caffeine_bliss.DTOs.UserDTO;
import com.ex.caffeine_bliss.DTOs.response.ResponseOrderDetailsDTO;
import com.ex.caffeine_bliss.entities.Order;
import com.ex.caffeine_bliss.entities.Receipt;
import com.ex.caffeine_bliss.exceptions.DuplicateElementException;
import com.ex.caffeine_bliss.exceptions.ResourceNotFoundException;
import com.ex.caffeine_bliss.repositories.OrderRepository;
import com.ex.caffeine_bliss.repositories.ReceiptRepository;
import com.ex.caffeine_bliss.services.EmailService;
import com.ex.caffeine_bliss.services.OrderService;
import com.ex.caffeine_bliss.services.ReceiptService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ReceiptDTO sentReceiptEmail(UUID orderId) {
        if (receiptRepository.existsByOrder_Id(orderId)) {
            throw new DuplicateElementException("Receipt already sent for this order.");
        }
        if(orderRepository.existsById(orderId)) {
            Order order = orderRepository.getOrderById(orderId);
            ResponseOrderDetailsDTO orderDetails = orderService.getOrdersDetailsById(orderId);
            Receipt receipt = new Receipt(order, orderDetails.getCustomerEmail());
            Receipt savedReceipt = receiptRepository.save(receipt);

            ReceiptEmailDTO emailDTO = new ReceiptEmailDTO(
                    savedReceipt.getId().toString(),
                    orderDetails.getOrderId().toString(),
                    orderDetails.getCustomerName(),
                    orderDetails.getCustomerMobile(),
                    orderDetails.getCreatedAt().toString(),
                    orderDetails.getTotalAmount(),
                    orderDetails.getItems()
            );

            byte[] header;
            byte[] footer;
            try {
                header = Files.readAllBytes(Paths.get("src/main/resources/static/images/header.png"));
                footer = Files.readAllBytes(Paths.get("src/main/resources/static/images/footer.png"));
            } catch (IOException e) {
                throw new RuntimeException("Failed to load email images", e);
            }

            emailService.sendReceiptEmail(
                    orderDetails.getCustomerEmail(), // to
                    "Your Caffeine Bliss Receipt ☕ - Order " + orderDetails.getOrderId(), // subject
                    emailDTO,
                    header,
                    footer
            );

            savedReceipt.setSentAt(new Date());
            receiptRepository.save(savedReceipt);

            return new ReceiptDTO(
                    savedReceipt.getId(),
                    order,
                    savedReceipt.getEmail(),
                    savedReceipt.getSentAt()
            );


        }else{
            throw new ResourceNotFoundException("Order not found for ID: " + orderId);
        }
    }

    @Override
    public ReceiptDTO getReceiptById(UUID id) {
        Receipt receipt = receiptRepository.getReferenceById(id);
        if(receipt != null){
            return modelMapper.map(receipt, ReceiptDTO.class);
        }else {
            throw new ResourceNotFoundException("There is no receipt with Id: " + id);
        }
    }

    @Override
    public ReceiptDTO getReceiptByOrderId(UUID orderId) {
        Receipt receipt = receiptRepository.findByOrder_Id(orderId);
        if(receipt != null){
            return modelMapper.map(receipt, ReceiptDTO.class);
        }else {
            throw new ResourceNotFoundException("There is no receipt with Order Id: " + orderId);
        }
    }
}
