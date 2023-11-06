package com.crio.jukebox.entities;

import java.util.List;

public class Playlist {

    private Long id;
    private String name;
    private Long userId;

    private List<Song> songs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Playlist(Long id, String name, Long userId, List<Song> songs) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.songs = songs;
    }

    public Playlist(String name, Long userId, List<Song> songs) {
        this.name = name;
        this.userId = userId;
        this.songs = songs;
    }

    public String getSongIds() {
        StringBuilder songIds = new StringBuilder("");
        for (Song song : this.getSongs()) {
            songIds.append(song.getId() + " ");
        }
        return songIds.toString().trim();
    }

    @Override
    public String toString() {
        return "Playlist [id=" + id + ", name=" + name + ", songs=" + songs + ", userId=" + userId
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((songs == null) ? 0 : songs.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Playlist other = (Playlist) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (songs == null) {
            if (other.songs != null)
                return false;
        } else if (!songs.equals(other.songs))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }
}
