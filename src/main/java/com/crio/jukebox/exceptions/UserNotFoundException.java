package com.crio.jukebox.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
