package com.crio.jukebox.exceptions;

public class PlaylistNotFoundException extends RuntimeException {
    PlaylistNotFoundException() {
        super();
    }

    public PlaylistNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
