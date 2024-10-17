package com.helmes.vladimirtest.exception;

public class SectorServiceLogicException extends Exception {
    public SectorServiceLogicException() {
        super("Something went wrong. Please try again later!");
    }
}