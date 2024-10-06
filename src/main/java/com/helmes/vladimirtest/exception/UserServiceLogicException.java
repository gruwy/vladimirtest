package com.helmes.vladimirtest.exception;

public class UserServiceLogicException extends Exception {

    public UserServiceLogicException() {
        super("Something went wrong. Please try again later!");
    }
}