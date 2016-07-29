
package com.grotesque.saa.player.data.naver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List_ {

    @SerializedName("time")
    @Expose
    private Double time;
    @SerializedName("source")
    @Expose
    private String source;

    /**
     * 
     * @return
     *     The time
     */
    public Double getTime() {
        return time;
    }

    /**
     * 
     * @param time
     *     The time
     */
    public void setTime(Double time) {
        this.time = time;
    }

    /**
     * 
     * @return
     *     The source
     */
    public String getSource() {
        return source;
    }

    /**
     * 
     * @param source
     *     The source
     */
    public void setSource(String source) {
        this.source = source;
    }

}
