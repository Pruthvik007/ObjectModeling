package com.crio.jukebox.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.SongRepository;

public class SongServices implements ISongServices {

    private SongRepository songRepository;

    private static final Integer columnNumberOfSongName = 1, columnNumberOfSongGenre = 2,
            columnNumberOfAlbumName = 3, columnNumberOfArtistName = 4,
            columnNumberOfOtherArtists = 5, totalNumberOfColumns = 6;

    public SongServices(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> loadSongs(String filePath) {
        Set<Song> songs = new LinkedHashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String row = bufferedReader.readLine();
            while (row != null) {
                String[] values = row.split(",");
                if (values != null && values.length > 1) {
                    String songName, genre, albumName;
                    List<String> artists = new ArrayList<>();
                    if (values.length == totalNumberOfColumns) {
                        songName = values[columnNumberOfSongName];
                        genre = values[columnNumberOfSongGenre];
                        albumName = values[columnNumberOfAlbumName];
                        artists.add(values[columnNumberOfArtistName]);
                        artists.addAll(extractArtists(values[columnNumberOfOtherArtists]));
                    } else {
                        songName = values[columnNumberOfSongName - 1];
                        genre = values[columnNumberOfSongGenre - 1];
                        albumName = values[columnNumberOfAlbumName - 1];
                        artists.add(values[columnNumberOfArtistName - 1]);
                        artists.addAll(extractArtists(values[columnNumberOfOtherArtists - 1]));
                    }
                    Song song = new Song(songName, genre, albumName, artists);
                    songs.add(song);
                }
                row = bufferedReader.readLine();
            }
            bufferedReader.close();
            return songRepository.addOrUpdateSongs(songs.stream().collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Song> getSongs(List<Long> songIds) {
        if(songIds==null){
            return null;
        }
        return songIds.stream().map(songId -> songRepository.getSong(songId))
                .collect(Collectors.toList());
    }

    private List<String> removeFirstArtist(List<String> artists) {
        artists.remove(0);
        return artists;
    }

    private List<String> extractArtists(String artists) {
        return removeFirstArtist(
                Arrays.asList(artists.split("#")).stream().collect(Collectors.toList()));
    }

}
