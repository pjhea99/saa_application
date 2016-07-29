package com.grotesque.saa.board.data;

import com.google.gson.annotations.SerializedName;
import com.grotesque.saa.home.data.DocumentList;

import java.util.ArrayList;

/**
 * Created by KH on 2016-07-26.
 */
public class NoticeContainer {
    @SerializedName("notice_list")
    ArrayList<DocumentList> documentLists ;

    public ArrayList<DocumentList> getDocumentList() {
        return documentLists;
    }
    public void setProjects(ArrayList<DocumentList> documentLists) {
        this.documentLists = documentLists;
    }
}
