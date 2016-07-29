package com.grotesque.saa.data;

/**
 * Created by 0614_000 on 2014-12-11.
 */
import android.os.Parcel;
import android.os.Parcelable;


public class BoardData implements Parcelable{

    private String title;
    private String id;
    private String nick;
    private String time;
    private String link;
    private String number;
    private String reply = "(0)";
    private String hit;
    private String img;
    private int padding;

    public BoardData(){

    }
    public BoardData(Parcel in) {
        readFromParcel(in);
    }

    public BoardData(String title, String id, String nick,
                     String time, String link, String number, String reply, String hit, String img, int padding) {
        super();
        this.title = title;
        this.id = id;
        this.nick = nick;
        this.time = time;
        this.link = link;
        this.number = number;
        if(!reply.equals("")){
            this.reply = reply;
        }
        this.hit = hit;
        this.img = img;
        this.padding = padding;

    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel arg0, int arg1) {
        arg0.writeString(title);
        arg0.writeString(id);
        arg0.writeString(nick);
        arg0.writeString(time);
        arg0.writeString(link);
        arg0.writeString(number);
        arg0.writeString(reply);
        arg0.writeString(hit);
        arg0.writeString(img);
        arg0.writeInt(padding);

    }
    private void readFromParcel(Parcel in){
        title = in.readString();
        id = in.readString();
        nick = in.readString();
        time = in.readString();
        link = in.readString();
        number = in.readString();
        reply = in.readString();
        hit = in.readString();
        img = in.readString();
        padding = in.readInt();
    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public BoardData createFromParcel(Parcel in) {
            return new BoardData(in);
        }

        @Override
        public BoardData[] newArray(int size) {
            return new BoardData[size];
        }
    };
}
