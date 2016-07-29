
package com.grotesque.saa.player.data.naver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Videos {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("hasPreview")
    @Expose
    private String hasPreview;
    @SerializedName("canAutoPlay")
    @Expose
    private Boolean canAutoPlay;
    @SerializedName("list")
    @Expose
    private java.util.List<List> list = new ArrayList<List>();

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The hasPreview
     */
    public String getHasPreview() {
        return hasPreview;
    }

    /**
     * 
     * @param hasPreview
     *     The hasPreview
     */
    public void setHasPreview(String hasPreview) {
        this.hasPreview = hasPreview;
    }

    /**
     * 
     * @return
     *     The canAutoPlay
     */
    public Boolean getCanAutoPlay() {
        return canAutoPlay;
    }

    /**
     * 
     * @param canAutoPlay
     *     The canAutoPlay
     */
    public void setCanAutoPlay(Boolean canAutoPlay) {
        this.canAutoPlay = canAutoPlay;
    }

    /**
     * 
     * @return
     *     The list
     */
    public java.util.List<List> getList() {
        return list;
    }

    /**
     * 
     * @param list
     *     The list
     */
    public void setList(java.util.List<List> list) {
        this.list = list;
    }

}
