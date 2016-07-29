
package com.grotesque.saa.player.data.naver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SplitVod {

    @SerializedName("version")
    @Expose
    private Double version;
    @SerializedName("fadeInTime")
    @Expose
    private Double fadeInTime;
    @SerializedName("fadeOutTime")
    @Expose
    private Double fadeOutTime;
    @SerializedName("playbackRewindTime")
    @Expose
    private Double playbackRewindTime;

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
     *     The fadeInTime
     */
    public Double getFadeInTime() {
        return fadeInTime;
    }

    /**
     * 
     * @param fadeInTime
     *     The fadeInTime
     */
    public void setFadeInTime(Double fadeInTime) {
        this.fadeInTime = fadeInTime;
    }

    /**
     * 
     * @return
     *     The fadeOutTime
     */
    public Double getFadeOutTime() {
        return fadeOutTime;
    }

    /**
     * 
     * @param fadeOutTime
     *     The fadeOutTime
     */
    public void setFadeOutTime(Double fadeOutTime) {
        this.fadeOutTime = fadeOutTime;
    }

    /**
     * 
     * @return
     *     The playbackRewindTime
     */
    public Double getPlaybackRewindTime() {
        return playbackRewindTime;
    }

    /**
     * 
     * @param playbackRewindTime
     *     The playbackRewindTime
     */
    public void setPlaybackRewindTime(Double playbackRewindTime) {
        this.playbackRewindTime = playbackRewindTime;
    }

}
