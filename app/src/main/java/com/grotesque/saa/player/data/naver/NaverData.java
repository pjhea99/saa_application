
package com.grotesque.saa.player.data.naver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class NaverData {

    @SerializedName("isMultiTrack")
    @Expose
    private Boolean isMultiTrack;
    @SerializedName("dimension")
    @Expose
    private String dimension;
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("videos")
    @Expose
    private Videos videos;
    @SerializedName("streams")
    @Expose
    private List<Stream> streams = new ArrayList<Stream>();
    @SerializedName("thumbnails")
    @Expose
    private Thumbnails_ thumbnails;
    @SerializedName("trackings")
    @Expose
    private Trackings trackings;

    /**
     * 
     * @return
     *     The isMultiTrack
     */
    public Boolean getIsMultiTrack() {
        return isMultiTrack;
    }

    /**
     * 
     * @param isMultiTrack
     *     The isMultiTrack
     */
    public void setIsMultiTrack(Boolean isMultiTrack) {
        this.isMultiTrack = isMultiTrack;
    }

    /**
     * 
     * @return
     *     The dimension
     */
    public String getDimension() {
        return dimension;
    }

    /**
     * 
     * @param dimension
     *     The dimension
     */
    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    /**
     * 
     * @return
     *     The meta
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     * 
     * @param meta
     *     The meta
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     * 
     * @return
     *     The videos
     */
    public Videos getVideos() {
        return videos;
    }

    /**
     * 
     * @param videos
     *     The videos
     */
    public void setVideos(Videos videos) {
        this.videos = videos;
    }

    /**
     * 
     * @return
     *     The streams
     */
    public List<Stream> getStreams() {
        return streams;
    }

    /**
     * 
     * @param streams
     *     The streams
     */
    public void setStreams(List<Stream> streams) {
        this.streams = streams;
    }

    /**
     * 
     * @return
     *     The thumbnails
     */
    public Thumbnails_ getThumbnails() {
        return thumbnails;
    }

    /**
     * 
     * @param thumbnails
     *     The thumbnails
     */
    public void setThumbnails(Thumbnails_ thumbnails) {
        this.thumbnails = thumbnails;
    }

    /**
     * 
     * @return
     *     The trackings
     */
    public Trackings getTrackings() {
        return trackings;
    }

    /**
     * 
     * @param trackings
     *     The trackings
     */
    public void setTrackings(Trackings trackings) {
        this.trackings = trackings;
    }

}
