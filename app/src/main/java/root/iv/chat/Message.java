package root.iv.chat;

import java.util.Date;

public class Message {
    private String author;
    private long time;
    private String content;

    public Message(String author, String content) {
        this.author = author;
        this.time = new Date().getTime();
        this.content = content;
    }

    public Message() {}

    public String getAuthor() {
        return author;
    }

    public long getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }
}
