package com.grotesque.saa.home.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by 경환 on 2016-04-03.
 */
public class DocumentContainer {
    @SerializedName("document_list")
    ArrayList<DocumentList> documentLists ;

    public ArrayList<DocumentList> getDocumentList() {
        return documentLists;
    }
    public void setProjects(ArrayList<DocumentList> documentLists) {
        this.documentLists = documentLists;
    }
}
