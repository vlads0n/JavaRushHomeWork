package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

/**
 * Created by Влад on 08.02.2016.
 */
public class Consumer implements Runnable {
    TransferQueue<ShareItem> queue;
    public Consumer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        if (Thread.currentThread().isInterrupted())
            Thread.currentThread().interrupt();
        try {
            Thread.currentThread().sleep(500);
            while(true) {
                ShareItem item = queue.take();
                System.out.println("Processing " + item.toString());
            }
        } catch (InterruptedException e) {
        }
    }
}
