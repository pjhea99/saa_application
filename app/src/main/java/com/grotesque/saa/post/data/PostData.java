package com.grotesque.saa.post.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 경환 on 2016-04-13.
 */
public class PostData implements Parcelable {
    String type;
    String html;
    String title;
    String content;
    String thumbnail;

    public PostData(String type, String html) {
        this.type = type;
        this.html = html;
        this.title = "";
        this.content = "";
        this.thumbnail = "";
    }

    public PostData(String type, String html, String title, String content, String thumbnail) {
        this.thumbnail = thumbnail;
        this.type = type;
        this.html = html;
        this.title = title;
        this.content = content;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(html);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(thumbnail);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PostData> CREATOR = new Creator<PostData>() {
        @Override
        public PostData createFromParcel(Parcel in) {
            return new PostData(in);
        }

        @Override
        public PostData[] newArray(int size) {
            return new PostData[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    protected PostData(Parcel in) {
        type = in.readString();
        html = in.readString();
        title = in.readString();
        content = in.readString();
        thumbnail = in.readString();
    }


}
