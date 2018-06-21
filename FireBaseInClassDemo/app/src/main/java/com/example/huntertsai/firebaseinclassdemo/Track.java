package com.example.huntertsai.firebaseinclassdemo;

/**
 * Created by huntertsai on 2018-02-26.
 */

public class Track {
    private String track, rating, trackId;

    public Track(){

    }

    public Track(String track, String rating, String trackId) {
        this.track = track;
        this.rating = rating;
        this.trackId = trackId;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
