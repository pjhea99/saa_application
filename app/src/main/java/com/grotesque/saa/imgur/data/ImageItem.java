package com.grotesque.saa.imgur.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 경환 on 2016-04-15.
 */
public class ImageItem implements Parcelable{
    @SerializedName("account_url")
    private String accountUrl;
    @SerializedName("animated")
    private boolean animated;
    @SerializedName("datetime")
    private long datetime;
    @SerializedName("deletehash")
    private String deletehash;
    @SerializedName("description")
    private String description;
    @SerializedName("favorite")
    private boolean favorite;
    @SerializedName("gifv")
    private String gifv;
    @SerializedName("height")
    private int height;
    @SerializedName("id")
    private String id;
    @SerializedName("link")
    private String link;
    @SerializedName("mp4")
    private String mp4;
    @SerializedName("nsfw")
    private Boolean nsfw;
    @SerializedName("size")
    private long size;
    @SerializedName("title")
    private String title;
    @SerializedName("width")
    private int width;

        protected ImageItem(Parcel in) {
            accountUrl = in.readString();
            animated = in.readByte() != 0;
            datetime = in.readLong();
            deletehash = in.readString();
            description = in.readString();
            favorite = in.readByte() != 0;
            gifv = in.readString();
            height = in.readInt();
            id = in.readString();
            link = in.readString();
            mp4 = in.readString();
            size = in.readLong();
            title = in.readString();
            width = in.readInt();
        }

        public static final Creator<ImageItem> CREATOR = new Creator<ImageItem>() {
            @Override
            public ImageItem createFromParcel(Parcel in) {
                return new ImageItem(in);
            }

            @Override
            public ImageItem[] newArray(int size) {
                return new ImageItem[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(accountUrl);
            dest.writeByte((byte) (animated ? 1 : 0));
            dest.writeLong(datetime);
            dest.writeString(deletehash);
            dest.writeString(description);
            dest.writeByte((byte) (favorite ? 1 : 0));
            dest.writeString(gifv);
            dest.writeInt(height);
            dest.writeString(id);
            dest.writeString(link);
            dest.writeString(mp4);
            dest.writeLong(size);
            dest.writeString(title);
            dest.writeInt(width);
        }

    public String getAccountUrl() {
        return accountUrl;
    }

    public void setAccountUrl(String accountUrl) {
        this.accountUrl = accountUrl;
    }

    public boolean isAnimated() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public String getDeletehash() {
        return deletehash;
    }

    public void setDeletehash(String deletehash) {
        this.deletehash = deletehash;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getGifv() {
        return gifv;
    }

    public void setGifv(String gifv) {
        this.gifv = gifv;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMp4() {
        return mp4;
    }

    public void setMp4(String mp4) {
        this.mp4 = mp4;
    }

    public Boolean getNsfw() {
        return nsfw;
    }

    public void setNsfw(Boolean nsfw) {
        this.nsfw = nsfw;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}

