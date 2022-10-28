package com.musictest.model;

public class Songs {
    private int _id;
    private int track;
    private String title;
    private int album;

    public int get_id() {
        return _id;
    }
    public void set_id(int _id) {
        this._id = _id;
    }

    public int getTrack() {
        return track;
    }
    public void setTrack(int track) {
        this.track = track;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getAlbum() {
        return album;
    }
    public void setAlbum(int album) {
        this.album = album;
    }
}
