
package com.grotesque.saa.player.data.naver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreloadingMidAd {

    @SerializedName("version")
    @Expose
    private Double version;
    @SerializedName("offset")
    @Expose
    private Double offset;
    @SerializedName("showNotice")
    @Expose
    private Boolean showNotice;
    @SerializedName("noticeTime")
    @Expose
    private Double noticeTime;

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

    /**
     * 
     * @return
     *     The showNotice
     */
    public Boolean getShowNotice() {
        return showNotice;
    }

    /**
     * 
     * @param showNotice
     *     The showNotice
     */
    public void setShowNotice(Boolean showNotice) {
        this.showNotice = showNotice;
    }

    /**
     * 
     * @return
     *     The noticeTime
     */
    public Double getNoticeTime() {
        return noticeTime;
    }

    /**
     * 
     * @param noticeTime
     *     The noticeTime
     */
    public void setNoticeTime(Double noticeTime) {
        this.noticeTime = noticeTime;
    }

}
