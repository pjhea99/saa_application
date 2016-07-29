package com.grotesque.saa.post.data;

import com.google.gson.annotations.SerializedName;
import com.grotesque.saa.home.data.DocumentList;

/**
 * Created by 경환 on 2016-04-14.
 */
public class ContentContainer {
    @SerializedName("error")
    int error;

    @SerializedName("message")
    String message;

    @SerializedName("oDocument")
    DocumentList documentList;

    public String getMessage() {
        return message;
    }

    public DocumentList getDocumentList() {
        return documentList;
    }
}
