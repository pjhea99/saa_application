package com.grotesque.saa.content.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 경환 on 2016-04-09.
 */
public class CommentData {
    @SerializedName("mid")
    private String mid;

    @SerializedName("document_srl")
    private String document_srl;

    @SerializedName("parent_srl")
    private String parent_srl;

    @SerializedName("comment_srl")
    private String comment_srl;

    @SerializedName("content")
    private String content;

    public CommentData(String mid, String document_srl, String parent_srl, String comment_srl, String content) {
        this.mid = mid;
        this.document_srl = document_srl;
        this.parent_srl = parent_srl;
        this.comment_srl = comment_srl;
        this.content = content;
    }

    public CommentData(String mid, String document_srl, String content) {
        this.mid = mid;
        this.document_srl = document_srl;
        this.content = content;
        this.comment_srl = "";
        this.parent_srl = "";
    }
}
