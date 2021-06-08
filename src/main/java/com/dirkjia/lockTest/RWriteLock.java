package com.dirkjia.lockTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWriteLock {
    private static final ReadWriteLock lock = new ReentrantReadWriteLock();
    private static final Lock readLock = lock.readLock();
    private static final Lock writeLock = lock.writeLock();

    private static final Map<String, Class<?>> udtfFunctions = new HashMap<>();

    public static void write(){
        System.out.println("add lock");
        writeLock.lock();
        udtfFunctions.put("1",Tagert.class);
        System.out.println("has written！");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        writeLock.unlock();
        System.out.println("release lock");
    }

    public static void read(){
        readLock.lock();
        if(udtfFunctions.containsKey("1")){
            System.out.println(udtfFunctions.get("1").toGenericString());
        } else {
            System.out.println("not found this!");
        }
        readLock.unlock();
    }

    private static class Tagert{

    }

}
