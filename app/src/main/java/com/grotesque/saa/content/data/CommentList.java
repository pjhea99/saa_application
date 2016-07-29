package com.grotesque.saa.content.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 경환 on 2016-04-06.
 */
public class CommentList implements Parcelable{
    @SerializedName("blamed_count")
    @Expose
    private String blamedCount;

    @SerializedName("comment_srl")
    @Expose
    private String commentSrl;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("depth")
    @Expose
    private String depth;

    @SerializedName("email_address")
    @Expose
    private String emailAddress;

    @SerializedName("homepage")
    @Expose
    private String homepage;

    @SerializedName("is_secret")
    @Expose
    private String isSecret;

    @SerializedName("last_update")
    @Expose
    private String lastUpdate;

    @SerializedName("nick_name")
    @Expose
    private String nickName;

    @SerializedName("parent_srl")
    @Expose
    private String parentSrl;

    @SerializedName("regdate")
    @Expose
    private String regdate;

    @SerializedName("user_id")
    @Expose
    private String userId;

    @SerializedName("user_name")
    @Expose
    private String userName;

    @SerializedName("votedCount")
    @Expose
    private String voted_count;

    public CommentList(String blamedCount, String commentSrl, String content, String depth, String emailAddress, String homepage, String isSecret, String lastUpdate, String nickName, String parentSrl, String regdate, String userId, String userName, String voted_count) {
        this.blamedCount = blamedCount;
        this.commentSrl = commentSrl;
        this.content = content;
        this.depth = depth;
        this.emailAddress = emailAddress;
        this.homepage = homepage;
        this.isSecret = isSecret;
        this.lastUpdate = lastUpdate;
        this.nickName = nickName;
        this.parentSrl = parentSrl;
        this.regdate = regdate;
        this.userId = userId;
        this.userName = userName;
        this.voted_count = voted_count;
    }

    public String getBlamedCount() {
        return blamedCount;
    }

    public void setBlamedCount(String blamedCount) {
        this.blamedCount = blamedCount;
    }

    public String getCommentSrl() {
        return commentSrl;
    }

    public void setCommentSrl(String commentSrl) {
        this.commentSrl = commentSrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getIsSecret() {
        return isSecret;
    }

    public void setIsSecret(String isSecret) {
        this.isSecret = isSecret;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getParentSrl() {
        return parentSrl;
    }

    public void setParentSrl(String parentSrl) {
        this.parentSrl = parentSrl;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVoted_count() {
        return voted_count;
    }

    public void setVoted_count(String voted_count) {
        this.voted_count = voted_count;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(blamedCount);
        dest.writeString(commentSrl);
        dest.writeString(content);
        dest.writeString(depth);
        dest.writeString(emailAddress);
        dest.writeString(homepage);
        dest.writeString(isSecret);
        dest.writeString(lastUpdate);
        dest.writeString(nickName);
        dest.writeString(parentSrl);
        dest.writeString(regdate);
        dest.writeString(userId);
        dest.writeString(userName);
        dest.writeString(voted_count);
    }
    private void readFromParcel(Parcel in){
        blamedCount = in.readString();
        commentSrl = in.readString();
        content = in.readString();
        depth = in.readString();
        emailAddress = in.readString();
        homepage = in.readString();
        isSecret = in.readString();
        lastUpdate = in.readString();
        nickName = in.readString();
        parentSrl = in.readString();
        regdate = in.readString();
        userId = in.readString();
        userName = in.readString();
        voted_count = in.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    public CommentList(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public CommentList createFromParcel(Parcel in) {
            return new CommentList(in);
        }

        @Override
        public CommentList[] newArray(int size) {
            return new CommentList[size];
        }
    };
}
