package com.javarush.test.level38.lesson06.home01;

/**
 * Created by Vinnik on 04.07.2016.
 */
public class ExceptionFactory {
    public static Throwable getException(Enum e) {
        if (e != null) {
            String message = e.toString().toLowerCase().replace('_', ' ');
            message = message.substring(0, 1).toUpperCase() + message.substring(1);
            if (e.getClass().equals(ExceptionApplicationMessage.class)) {
                return new Exception(message);
            } else if (e.getClass().equals(ExceptionUserMessage.class)) {
                return new Error(message);
            } else if (e.getClass().equals(ExceptionDBMessage.class)) {
                return new RuntimeException(message);
            }
        }
        return new IllegalArgumentException();
    }
}
