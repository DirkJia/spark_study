package com.dirkjia.lockTest;

public class RWriteLockTest {

    public static void main(String[] args) {
        new Thread(RWriteLock::write).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                RWriteLock.read();
            }
        }).start();
    }
}
