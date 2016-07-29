package com.grotesque.saa.search.data;

import com.google.gson.annotations.SerializedName;
import com.grotesque.saa.home.data.DocumentList;

/**
 * Created by 경환 on 2016-05-21.
 */
public class SearchItemContainer {
    @SerializedName("oDocument")
    DocumentList documentList;

    public DocumentList getDocumentList() {
        return documentList;
    }

    public void setDocumentList(DocumentList documentList) {
        this.documentList = documentList;
    }
}
