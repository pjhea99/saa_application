
package com.grotesque.saa.player.data.naver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bitrate {

    @SerializedName("video")
    @Expose
    private Double video;
    @SerializedName("audio")
    @Expose
    private Double audio;

    /**
     * 
     * @return
     *     The video
     */
    public Double getVideo() {
        return video;
    }

    /**
     * 
     * @param video
     *     The video
     */
    public void setVideo(Double video) {
        this.video = video;
    }

    /**
     * 
     * @return
     *     The audio
     */
    public Double getAudio() {
        return audio;
    }

    /**
     * 
     * @param audio
     *     The audio
     */
    public void setAudio(Double audio) {
        this.audio = audio;
    }

}
