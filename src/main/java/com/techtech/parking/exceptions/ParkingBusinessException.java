package com.techtech.parking.exceptions;

public class ParkingBusinessException extends RuntimeException {
    public ParkingBusinessException(String message) {
        super(message);
    }
}