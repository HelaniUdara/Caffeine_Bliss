package com.ex.caffeine_bliss.services;

import com.ex.caffeine_bliss.DTOs.paginated.PaginatedResponse;
import com.ex.caffeine_bliss.DTOs.request.RequestSaveOrderDTO;
import com.ex.caffeine_bliss.DTOs.response.ResponseOrderDetailsDTO;
import com.ex.caffeine_bliss.DTOs.response.ResponseOrderSummaryDTO;

import java.util.UUID;

public interface OrderService {
    String addOrder(RequestSaveOrderDTO saveOrderDTO);

    PaginatedResponse<ResponseOrderSummaryDTO> getOrdersByCustomerId(UUID customerId, int page, int limit);
    PaginatedResponse<ResponseOrderSummaryDTO> getOrdersByCashierId(UUID customerId, int page, int limit);

    PaginatedResponse<ResponseOrderSummaryDTO> getAllOrders(int page, int limit);

    ResponseOrderDetailsDTO getOrdersDetailsById(UUID id);
}
