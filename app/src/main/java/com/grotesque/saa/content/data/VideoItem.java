package com.grotesque.saa.content.data;

import org.jsoup.nodes.Element;

public class VideoItem{
    private String vid;
    private String outKey;
    private Element url;
    private int type;

    public VideoItem(String vid, String outKey, Element url, int type) {
        this.vid = vid;
        this.outKey = outKey;
        this.url = url;
        this.type = type;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getOutKey() {
        return outKey;
    }

    public void setOutKey(String outKey) {
        this.outKey = outKey;
    }

    public Element getUrl() {
        return url;
    }

    public void setUrl(Element url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}