package com.ex.caffeine_bliss.controllers;

import com.ex.caffeine_bliss.DTOs.ReceiptDTO;
import com.ex.caffeine_bliss.DTOs.UserDTO;
import com.ex.caffeine_bliss.DTOs.response.ResponseReceiptDetailsDTO;
import com.ex.caffeine_bliss.services.ReceiptService;
import com.ex.caffeine_bliss.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/receipt")
public class ReceiptController {
    @Autowired
    private ReceiptService receiptService;

    @GetMapping(path = "/get-by-Id", params = "id")
    public ResponseEntity<StandardResponse> getReceiptById(@RequestParam(value = "id") UUID id){
        ResponseReceiptDetailsDTO receiptDTO = receiptService.getReceiptById(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        receiptDTO), HttpStatus.OK);
    }

    @GetMapping(path = "/get-by-orderId", params = "id")
    public ResponseEntity<StandardResponse> getReceiptByOrderId(@RequestParam(value = "id") UUID orderId){
        ResponseReceiptDetailsDTO receiptDTO = receiptService.getReceiptByOrderId(orderId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        receiptDTO), HttpStatus.OK);
    }

    @PostMapping(path = "/sent-email", params = "id")
    public ResponseEntity<StandardResponse> sendReceiptEmail(@RequestParam(value = "id") UUID orderId){
        ReceiptDTO receipt = receiptService.sentReceiptEmail(orderId);
        String message = (receipt != null) ? "Receipt sent!" : "Receipt did not recorded.";
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        message), HttpStatus.OK);
    }
}
