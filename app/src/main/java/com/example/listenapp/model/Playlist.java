package com.example.listenapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Playlist implements Parcelable {

    private String playlistName;
    private Integer quantity, playlistImage;

    public Playlist() {
        quantity = 0;
    }
    public Playlist(String playlistName, int quantity, @Nullable Integer playlistImage) {
       this.playlistName = playlistName;
       this.quantity = quantity;
       this.playlistImage = playlistImage;
    }
    protected Playlist(Parcel in) {
        playlistName = in.readString();
        quantity = in.readInt();
        if (in.readByte() == 0) {
            playlistImage = null;
        } else {
            playlistImage = in.readInt();
        }
    }

    public static final Creator<Playlist> CREATOR = new Creator<Playlist>() {
        @Override
        public Playlist createFromParcel(Parcel in) {
            return new Playlist(in);
        }

        @Override
        public Playlist[] newArray(int size) {
            return new Playlist[size];
        }
    };

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPlaylistImage() {
        return playlistImage;
    }

    public void setPlaylistImage(Integer playlistImage) {
        this.playlistImage = playlistImage;
    }
    @NonNull
    @Override
    public String toString() {
        return playlistName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(playlistName);
        dest.writeInt(quantity);
        if (playlistImage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(playlistImage);
        }
    }
}
