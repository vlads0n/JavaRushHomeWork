package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

/**
 * Created by Влад on 08.02.2016.
 */
public class Producer implements Runnable {
    private TransferQueue<ShareItem> queue;
    public Producer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        if (Thread.currentThread().isInterrupted())
            Thread.currentThread().interrupt();
        for (int i = 1; i < 10; i++) {
            System.out.format("Элемент 'ShareItem-%d' добавлен", i);
            System.out.println();
            queue.offer(new ShareItem("ShareItem-" + i, i));
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
            }
            if (queue.hasWaitingConsumer())
                System.out.println("Consumer в ожидании!");
        }
    }

}
