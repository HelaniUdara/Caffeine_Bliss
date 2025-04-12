package com.ex.caffeine_bliss.services;

import com.ex.caffeine_bliss.DTOs.ReceiptEmailDTO;

public interface EmailService {
    void sendReceiptEmail(String to, String subject, ReceiptEmailDTO data, byte[] headerImg, byte[] footerImg);
}
