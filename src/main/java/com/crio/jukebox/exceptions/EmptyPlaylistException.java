package com.crio.jukebox.exceptions;

public class EmptyPlaylistException extends RuntimeException {
    public EmptyPlaylistException() {
        super();
    }

    public EmptyPlaylistException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
