package com.grotesque.saa.search.data;

/**
 * Created by 경환 on 2016-05-21.
 */
public class SearchItem {
    private String title;
    private String author;
    private String hit;
    private String reply;
    private String date;
    private String document_srl;

    public SearchItem(String title, String author, String hit, String reply, String date, String document_srl) {
        this.title = title;
        this.author = author;
        this.hit = hit;
        this.reply = reply;
        this.date = date;
        this.document_srl = document_srl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDocument_srl() {
        return document_srl;
    }

    public void setDocument_srl(String document_srl) {
        this.document_srl = document_srl;
    }
}
