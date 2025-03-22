/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpay.common;

import dal.OrderDAO;
import java.io.IOException;import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Order;

/**
 *
 * @author CTT VNPAY
 */
@WebServlet("/vnpayajax")
public class ajaxServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        Account auth = (Account) session.getAttribute("auth");

        if (auth == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String bankCode = req.getParameter("bankCode");
        String amountRaw = req.getParameter("totalBill");

        if(amountRaw == null || amountRaw.trim().isEmpty()) {
            resp.sendRedirect("cart.jsp");
            return;
        }

        double amountDouble = Double.parseDouble(amountRaw);
        OrderDAO orderDAO = new OrderDAO();

        // Tạo order
        Order order = new Order();
        order.setAccountId(auth.getUserId());
        order.setTotalAmount(amountDouble);
        order.setStatus("Pending");

        int orderId = orderDAO.insertOrder(order);

        if(orderId < 1) {
            resp.sendRedirect("vnpay_pay.jsp");
            return;
        }

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "billpayment";

        long amount = (long) (amountDouble * 100);
        String vnp_TxnRef = String.valueOf(orderId);
        String vnp_IpAddr = Config.getIpAddress(req);

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", Config.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        if(bankCode != null && !bankCode.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bankCode);
        }

        vnp_Params.put("vnp_Locale", "vn");

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        vnp_Params.put("vnp_CreateDate", formatter.format(cld.getTime()));

        cld.add(Calendar.MINUTE, 15);
        vnp_Params.put("vnp_ExpireDate", formatter.format(cld.getTime()));

        // Xây dựng URL
        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();

        for(String fieldName : fieldNames) {
            String fieldValue = vnp_Params.get(fieldName);
            hashData.append(fieldName).append('=').append(URLEncoder.encode(fieldValue, "UTF-8"));
            query.append(URLEncoder.encode(fieldName, "UTF-8")).append('=').append(URLEncoder.encode(fieldValue, "UTF-8"));
            query.append('&');
            hashData.append('&');
        }

        String queryUrl = query.substring(0, query.length() - 1);
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.substring(0, hashData.length() - 1));
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;

        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;

        resp.sendRedirect(paymentUrl);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        Account auth = (Account) session.getAttribute("auth");
        
        if (auth == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        
        if(req.getParameter("totalBill") == null){
            resp.sendRedirect("error_404.jsp");
            return;
        }

        String bankCode = req.getParameter("bankCode");
        double amountDouble = Double.parseDouble(req.getParameter("totalBill"));

        OrderDAO orderDAO = new OrderDAO();
        int userId = Integer.parseInt(req.getParameter("userId"));
        // Tạo order
        Order order = new Order();
        order.setAccountId(auth.getUserId());
        order.setTotalAmount(amountDouble);
//        order.setStatus("Pending");

        int orderId = orderDAO.insertOrder(order);

        if(orderId < 1) {
            resp.sendRedirect("error_404.jsp");
            return;
        }

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "billpayment";

        long amount = (long) (amountDouble * 100);
        String vnp_TxnRef = String.valueOf(orderId);
        String vnp_IpAddr = Config.getIpAddress(req);

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", Config.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        if(bankCode != null && !bankCode.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bankCode);
        }

        vnp_Params.put("vnp_Locale", "vn");

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        vnp_Params.put("vnp_CreateDate", formatter.format(cld.getTime()));

        cld.add(Calendar.MINUTE, 15);
        vnp_Params.put("vnp_ExpireDate", formatter.format(cld.getTime()));

        // Xây dựng URL
        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();

        for(String fieldName : fieldNames) {
            String fieldValue = vnp_Params.get(fieldName);
            hashData.append(fieldName).append('=').append(URLEncoder.encode(fieldValue, "UTF-8"));
            query.append(URLEncoder.encode(fieldName, "UTF-8")).append('=').append(URLEncoder.encode(fieldValue, "UTF-8"));
            query.append('&');
            hashData.append('&');
        }

        String queryUrl = query.substring(0, query.length() - 1);
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.substring(0, hashData.length() - 1));
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;

        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;

        resp.sendRedirect(paymentUrl);
    }
}

