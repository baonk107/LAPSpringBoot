package edu.hanoi.spring.model;

public class Message {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Message() {
    }

    public Message(String content) {
        this.content = content;
    }
}
