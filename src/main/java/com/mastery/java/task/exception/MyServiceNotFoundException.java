package com.mastery.java.task.exception;

public class MyServiceNotFoundException extends RuntimeException {

    public MyServiceNotFoundException(String msg) {
        super(msg);
    }
}
