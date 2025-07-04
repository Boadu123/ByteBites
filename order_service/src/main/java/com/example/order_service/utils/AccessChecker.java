package com.example.order_service.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

@Component("accessChecker")
public class AccessChecker {
    public Boolean isCustomer(String roles) {
        return roles.equals("ROLE_CUSTOMER");
    }
}

