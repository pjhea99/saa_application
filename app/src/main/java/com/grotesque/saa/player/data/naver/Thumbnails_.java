
package com.grotesque.saa.player.data.naver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Thumbnails_ {

    @SerializedName("list")
    @Expose
    private List<List_> list = new ArrayList<List_>();

    /**
     * 
     * @return
     *     The list
     */
    public List<List_> getList() {
        return list;
    }

    /**
     * 
     * @param list
     *     The list
     */
    public void setList(List<List_> list) {
        this.list = list;
    }

}
