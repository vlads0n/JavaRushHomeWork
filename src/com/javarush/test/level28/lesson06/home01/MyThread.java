package com.javarush.test.level28.lesson06.home01;

/**
 * Created by Влад on 29.12.2015.
 */
public class MyThread extends Thread
{
    private static int i = MIN_PRIORITY;
    public MyThread()
    {
        if (i <= MAX_PRIORITY)
            this.setPriority(i++);
        else
        {
            i = MIN_PRIORITY;
            this.setPriority(i++);
        }
    }

    public MyThread(ThreadGroup group, String name)
    {
        super(group, name);
        if (i <= group.getMaxPriority())
            this.setPriority(i++);
        else if (i <= MAX_PRIORITY)
        {
            this.setPriority(group.getMaxPriority());
            i++;
        }
        else
        {
            i = MIN_PRIORITY;
            this.setPriority(i++);
        }
    }

    public MyThread(Runnable target)
    {
        super(target);

        if (i <= MAX_PRIORITY)
            this.setPriority(i++);
        else
        {
            i = MIN_PRIORITY;
            this.setPriority(i++);
        }
    }

    public MyThread(ThreadGroup group, Runnable target)
    {
        super(group, target);
        if (i <= group.getMaxPriority())
            this.setPriority(i++);
        else if (i <= MAX_PRIORITY)
        {
            this.setPriority(group.getMaxPriority());
            i++;
        }
        else
        {
            i = MIN_PRIORITY;
            this.setPriority(i++);
        }
    }

    public MyThread(String name)
    {
        super(name);

        if (i <= MAX_PRIORITY)
            this.setPriority(i++);
        else
        {
            i = MIN_PRIORITY;
            this.setPriority(i++);
        }
    }

    public MyThread(Runnable target, String name)
    {
        super(target, name);

        if (i <= MAX_PRIORITY)
            this.setPriority(i++);
        else
        {
            i = MIN_PRIORITY;
            this.setPriority(i++);
        }
    }

    public MyThread(ThreadGroup group, Runnable target, String name)
    {
        super(group, target, name);

        if (i <= group.getMaxPriority())
            this.setPriority(i++);
        else if (i <= MAX_PRIORITY)
        {
            this.setPriority(group.getMaxPriority());
            i++;
        }
        else
        {
            i = MIN_PRIORITY;
            this.setPriority(i++);
        }
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize)
    {
        super(group, target, name, stackSize);

        if (i <= group.getMaxPriority())
            this.setPriority(i++);
        else if (i <= MAX_PRIORITY)
        {
            this.setPriority(group.getMaxPriority());
            i++;
        }
        else
        {
            i = MIN_PRIORITY;
            this.setPriority(i++);
        }
    }
}
