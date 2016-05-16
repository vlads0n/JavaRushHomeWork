package com.javarush.test.level30.lesson06.home01;

import java.util.concurrent.RecursiveTask;

/**
 * Created by Влад on 08.02.2016.
 */
public class BinaryRepresentationTask extends RecursiveTask<String> {
    private int task;
    public BinaryRepresentationTask(int i) {
        this.task = i;
    }

    @Override
    protected String compute() {
        int a = task % 2;
        int b = task / 2;
        String result = String.valueOf(a);
        if (b > 0)
        {
            BinaryRepresentationTask binaryRepresentationTask = new BinaryRepresentationTask(a);
            binaryRepresentationTask.fork();
            return new BinaryRepresentationTask(b).compute() + binaryRepresentationTask.join();
        }
        return result;
    }
}
