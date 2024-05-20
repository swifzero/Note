package com.example.note;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.litepal.crud.LitePalSupport;

public class Note extends LitePalSupport {

    private Long id;
    private String title;
    private String body;

    public Note(String title, String body, Long id){
        this.title = title;
        this.body = body;
        this.id = id;
    }

    public Note(Note note){
        this.title = note.getTitle();
        this.body = note.getBody();
        this.id = note.getId();
    }

    public Note() {
        this.title = "";
        this.body = "";
    }

    public String getTitle(){
        return title;
    }

    public String getBody() {
        return body;
    }

    public Long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

}

