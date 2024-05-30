package com.turkcell.orderService.util;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class OrderNumberGenerator {
    public static String generateOrderNumber() {
        Random random = new Random();
        StringBuilder orderNumber = new StringBuilder("TR");

        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10); // 0-9 arası rastgele bir rakam
            orderNumber.append(digit);
        }

        return orderNumber.toString();
    }
}
