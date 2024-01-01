package com.example.beecommerce.controller;


import com.example.beecommerce.pojo.entity.Payment;
import com.example.beecommerce.pojo.requests.PaymentRequest;
import com.example.beecommerce.pojo.requests.VnpayRequest;
import com.example.beecommerce.pojo.responses.ObjectResponse;
import com.example.beecommerce.pojo.responses.PaymentResponse;
import com.example.beecommerce.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/payment")
@CrossOrigin(maxAge = 3600)
@Slf4j
public class VnpayController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/vnpay")
    public ResponseEntity<ObjectResponse> createVnpay(@RequestBody VnpayRequest vnpayRequest) throws UnsupportedEncodingException {
        PaymentResponse paymentResponse = paymentService.createVnpay(vnpayRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ObjectResponse(HttpStatus.OK, "Create payment vnpay successfully", paymentResponse)
        );
    }

    @PostMapping("/create")
    public ResponseEntity<ObjectResponse> createPayment(@RequestBody PaymentRequest paymentRequest) {
        Payment payment = paymentService.createPayment(paymentRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ObjectResponse(HttpStatus.OK, "Create payment successfully", payment)
        );
    }

    @GetMapping("")
    public ResponseEntity<ObjectResponse> getListPayments() {
        List<Payment> paymentList = paymentService.getListPayments();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ObjectResponse(HttpStatus.OK, "Query list payment successfully", paymentList)
        );
    }

    @GetMapping("/vnpay/success")
    public RedirectView getVnpaySuccess(
            @RequestParam("vnp_ResponseCode") String vnpResponseCode,
            @RequestParam("list_id") List<Long> list
    ) throws URISyntaxException {
        return paymentService.paymentSuccess(vnpResponseCode, list);
    }
}
