package com.example.springaop.service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SpaPrincessA implements SPAService {

    private Random random = new Random();

    private int bound = 5;
    @Override
    public void fullBodyMassage(String customer) {
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(bound));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(customer + "享受完全身按摩服务");
    }

    @Override
    public void aromaOilMassage(String customer) {
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(bound));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(customer + "享受完精油按摩服务");
    }

}
