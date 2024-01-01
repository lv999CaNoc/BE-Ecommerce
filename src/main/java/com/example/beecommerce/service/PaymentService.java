package com.example.beecommerce.service;

import com.example.beecommerce.pojo.entity.Payment;
import com.example.beecommerce.pojo.requests.PaymentRequest;
import com.example.beecommerce.pojo.requests.VnpayRequest;
import com.example.beecommerce.pojo.responses.PaymentResponse;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

public interface PaymentService {
    PaymentResponse createVnpay(VnpayRequest vnpayRequest) throws UnsupportedEncodingException;
    List<Payment> getListPayments();
    Payment createPayment(PaymentRequest paymentRequest);
    RedirectView paymentSuccess(String vnp_ResponseCode, List<Long> listId) throws URISyntaxException;
}
