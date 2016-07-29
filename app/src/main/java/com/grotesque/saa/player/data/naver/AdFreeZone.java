
package com.grotesque.saa.player.data.naver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdFreeZone {

    @SerializedName("version")
    @Expose
    private Double version;
    @SerializedName("zone")
    @Expose
    private String zone;
    @SerializedName("offset")
    @Expose
    private Double offset;

    /**
     * 
     * @return
     *     The version
     */
    public Double getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     *     The version
     */
    public void setVersion(Double version) {
        this.version = version;
    }

    /**
     * 
     * @return
     *     The zone
     */
    public String getZone() {
        return zone;
    }

    /**
     * 
     * @param zone
     *     The zone
     */
    public void setZone(String zone) {
        this.zone = zone;
    }

    /**
     * 
     * @return
     *     The offset
     */
    public Double getOffset() {
        return offset;
    }

    /**
     * 
     * @param offset
     *     The offset
     */
    public void setOffset(Double offset) {
        this.offset = offset;
    }

}
