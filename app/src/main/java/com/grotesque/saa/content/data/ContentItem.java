package com.grotesque.saa.content.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.grotesque.saa.home.data.DocumentList;

public class ContentItem implements Parcelable {



    private String text;
    private String img;
    private VideoItem video;
    private String type;
    private boolean animated;
    private DocumentList documentData;

    public ContentItem() {
    }

    public ContentItem(String text, String img, VideoItem video, String type, boolean animated) {
        this.text = text;
        this.img = img;
        this.video = video;
        this.type = type;
        this.animated = animated;
    }

    public ContentItem(String type, DocumentList boardData) {
        this.type = type;
        this.documentData = boardData;
    }

    protected ContentItem(Parcel in) {
        text = in.readString();
        img = in.readString();
        type = in.readString();
        animated = in.readByte() != 0;
        documentData = in.readParcelable(DocumentList.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeString(img);
        dest.writeString(type);
        dest.writeByte((byte) (animated ? 1 : 0));
        dest.writeParcelable(documentData, flags);
    }

    public static final Creator<ContentItem> CREATOR = new Creator<ContentItem>() {
        @Override
        public ContentItem createFromParcel(Parcel in) {
            return new ContentItem(in);
        }

        @Override
        public ContentItem[] newArray(int size) {
            return new ContentItem[size];
        }
    };

    public DocumentList getDocumentData() {
        return documentData;
    }

    public void setDocumentData(DocumentList documentData) {
        this.documentData = documentData;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public VideoItem getVideo() {
        return video;
    }

    public void setVideo(VideoItem video) {
        this.video = video;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAnimated() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }


    @Override
    public int describeContents() {
        return 0;
    }

}
