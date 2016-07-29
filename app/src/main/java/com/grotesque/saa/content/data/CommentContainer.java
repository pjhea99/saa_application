package com.grotesque.saa.content.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by 경환 on 2016-04-06.
 */
public class CommentContainer {
    @SerializedName("comment_list")
    ArrayList<CommentList> commentLists ;

    public ArrayList<CommentList> getCommentList() {
        return commentLists;
    }
    public void setProjects(ArrayList<CommentList> documentLists) {
        this.commentLists = documentLists;
    }
}
