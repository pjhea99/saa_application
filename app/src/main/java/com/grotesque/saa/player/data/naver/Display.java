
package com.grotesque.saa.player.data.naver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Display {

    @SerializedName("screenClickPlay")
    @Expose
    private ScreenClickPlay screenClickPlay;
    @SerializedName("logo")
    @Expose
    private Logo logo;
    @SerializedName("playbackRate")
    @Expose
    private PlaybackRate playbackRate;
    @SerializedName("scrap")
    @Expose
    private Scrap scrap;
    @SerializedName("thumbnails")
    @Expose
    private Thumbnails thumbnails;
    @SerializedName("vodinfo")
    @Expose
    private Vodinfo vodinfo;
    @SerializedName("script")
    @Expose
    private Script script;
    @SerializedName("setting")
    @Expose
    private Setting setting;
    @SerializedName("fullscreen")
    @Expose
    private Fullscreen fullscreen;
    @SerializedName("seekable")
    @Expose
    private Seekable seekable;
    @SerializedName("subtitle")
    @Expose
    private Subtitle subtitle;
    @SerializedName("quality")
    @Expose
    private Quality quality;
    @SerializedName("expand")
    @Expose
    private Expand expand;
    @SerializedName("playButton")
    @Expose
    private PlayButton playButton;

    /**
     * 
     * @return
     *     The screenClickPlay
     */
    public ScreenClickPlay getScreenClickPlay() {
        return screenClickPlay;
    }

    /**
     * 
     * @param screenClickPlay
     *     The screenClickPlay
     */
    public void setScreenClickPlay(ScreenClickPlay screenClickPlay) {
        this.screenClickPlay = screenClickPlay;
    }

    /**
     * 
     * @return
     *     The logo
     */
    public Logo getLogo() {
        return logo;
    }

    /**
     * 
     * @param logo
     *     The logo
     */
    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    /**
     * 
     * @return
     *     The playbackRate
     */
    public PlaybackRate getPlaybackRate() {
        return playbackRate;
    }

    /**
     * 
     * @param playbackRate
     *     The playbackRate
     */
    public void setPlaybackRate(PlaybackRate playbackRate) {
        this.playbackRate = playbackRate;
    }

    /**
     * 
     * @return
     *     The scrap
     */
    public Scrap getScrap() {
        return scrap;
    }

    /**
     * 
     * @param scrap
     *     The scrap
     */
    public void setScrap(Scrap scrap) {
        this.scrap = scrap;
    }

    /**
     * 
     * @return
     *     The thumbnails
     */
    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    /**
     * 
     * @param thumbnails
     *     The thumbnails
     */
    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    /**
     * 
     * @return
     *     The vodinfo
     */
    public Vodinfo getVodinfo() {
        return vodinfo;
    }

    /**
     * 
     * @param vodinfo
     *     The vodinfo
     */
    public void setVodinfo(Vodinfo vodinfo) {
        this.vodinfo = vodinfo;
    }

    /**
     * 
     * @return
     *     The script
     */
    public Script getScript() {
        return script;
    }

    /**
     * 
     * @param script
     *     The script
     */
    public void setScript(Script script) {
        this.script = script;
    }

    /**
     * 
     * @return
     *     The setting
     */
    public Setting getSetting() {
        return setting;
    }

    /**
     * 
     * @param setting
     *     The setting
     */
    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    /**
     * 
     * @return
     *     The fullscreen
     */
    public Fullscreen getFullscreen() {
        return fullscreen;
    }

    /**
     * 
     * @param fullscreen
     *     The fullscreen
     */
    public void setFullscreen(Fullscreen fullscreen) {
        this.fullscreen = fullscreen;
    }

    /**
     * 
     * @return
     *     The seekable
     */
    public Seekable getSeekable() {
        return seekable;
    }

    /**
     * 
     * @param seekable
     *     The seekable
     */
    public void setSeekable(Seekable seekable) {
        this.seekable = seekable;
    }

    /**
     * 
     * @return
     *     The subtitle
     */
    public Subtitle getSubtitle() {
        return subtitle;
    }

    /**
     * 
     * @param subtitle
     *     The subtitle
     */
    public void setSubtitle(Subtitle subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * 
     * @return
     *     The quality
     */
    public Quality getQuality() {
        return quality;
    }

    /**
     * 
     * @param quality
     *     The quality
     */
    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    /**
     * 
     * @return
     *     The expand
     */
    public Expand getExpand() {
        return expand;
    }

    /**
     * 
     * @param expand
     *     The expand
     */
    public void setExpand(Expand expand) {
        this.expand = expand;
    }

    /**
     * 
     * @return
     *     The playButton
     */
    public PlayButton getPlayButton() {
        return playButton;
    }

    /**
     * 
     * @param playButton
     *     The playButton
     */
    public void setPlayButton(PlayButton playButton) {
        this.playButton = playButton;
    }

}
