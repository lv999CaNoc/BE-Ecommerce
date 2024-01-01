package com.example.beecommerce.service.implement;

import com.example.beecommerce.config.VnpayConfig;
import com.example.beecommerce.pojo.entity.Payment;
import com.example.beecommerce.pojo.requests.PaymentRequest;
import com.example.beecommerce.pojo.requests.VnpayRequest;
import com.example.beecommerce.pojo.responses.PaymentResponse;
import com.example.beecommerce.repository.PaymentRepository;
import com.example.beecommerce.service.OrderService;
import com.example.beecommerce.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class PaymentImplementService implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderService orderService;

    @Override
    public PaymentResponse createVnpay(VnpayRequest vnpayRequest) {
        List<Long> listId = new ArrayList<Long>(Arrays.asList(vnpayRequest.getList_id_order()));
        StringJoiner joiner = new StringJoiner(",");
        for (Long id : listId) {
            joiner.add(String.valueOf(id));
        }
        String result = joiner.toString();
        String returnUrl = "http://localhost:8080/api/v1/payment/vnpay/success?list_id=" + result;

        String vnp_TxnRef = VnpayConfig.getRandomNumber(8);

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", VnpayConfig.vnp_Version);
        vnp_Params.put("vnp_Command", VnpayConfig.vnp_Command);
        vnp_Params.put("vnp_TmnCode", VnpayConfig.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(vnpayRequest.getAmount()));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "NCB");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", vnpayRequest.getContent_pay() + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", "order-type");
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", returnUrl);
        vnp_Params.put("vnp_IpAddr", "127.0.0.1");

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                try {
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VnpayConfig.hmacSHA512(VnpayConfig.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VnpayConfig.vnp_PayUrl + "?" + queryUrl;

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setStatus("Ok");
        paymentResponse.setMessage("Successfully");
        paymentResponse.setURL(paymentUrl);
        return paymentResponse;
    }

    @Override
    public List<Payment> getListPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment createPayment(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setName_payment(paymentRequest.getName_payment());
        return paymentRepository.save(payment);
    }

    @Override
    public RedirectView paymentSuccess(String vnp_ResponseCode, List<Long> listId) throws URISyntaxException {
        RedirectView redirectView = new RedirectView("http://localhost:3000/account/purchase");
        if (!vnp_ResponseCode.equals("00")) {
            return redirectView;
        }
        orderService.updateListOrder(listId);
        return redirectView;
    }

}
