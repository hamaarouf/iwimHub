package com.example.iwimhub.Models;

public class Message {
    private String id;
    private String toId;
    private String fromFullname;
    private String message;

    public Message( String fromFullname, String toId,String message) {
        //this.id = id;
        this.toId = toId;
        this.fromFullname = fromFullname;
        this.message = message;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public void setFromFullname(String fromFullname) {
        this.fromFullname = fromFullname;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getToId() {
        return toId;
    }

    public String getFromFullname() {
        return fromFullname;
    }

    public String getMessage() {
        return message;
    }
}
