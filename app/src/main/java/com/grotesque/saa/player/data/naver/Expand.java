
package com.grotesque.saa.player.data.naver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Expand {

    @SerializedName("visible")
    @Expose
    private String visible;
    @SerializedName("value")
    @Expose
    private String value;

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

    /**
     * 
     * @return
     *     The value
     */
    public String getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    public void setValue(String value) {
        this.value = value;
    }

}
