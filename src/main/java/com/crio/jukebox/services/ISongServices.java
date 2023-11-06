package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.Song;

public interface ISongServices {

    public List<Song> loadSongs(String filePath);

    public List<Song> getSongs(List<Long> songIds);
}
