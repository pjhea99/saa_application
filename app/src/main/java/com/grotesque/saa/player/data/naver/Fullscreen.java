
package com.grotesque.saa.player.data.naver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fullscreen {

    @SerializedName("visible")
    @Expose
    private String visible;

    /**
     * 
     * @return
     *     The visible
     */
    public String getVisible() {
        return visible;
    }

    /**
     * 
     * @param visible
     *     The visible
     */
    public void setVisible(String visible) {
        this.visible = visible;
    }

}
