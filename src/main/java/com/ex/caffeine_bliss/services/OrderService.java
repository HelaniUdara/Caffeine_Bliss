package com.ex.caffeine_bliss.services;

import com.ex.caffeine_bliss.DTOs.request.RequestSaveOrderDTO;

public interface OrderService {
    String addOrder(RequestSaveOrderDTO saveOrderDTO);
}
