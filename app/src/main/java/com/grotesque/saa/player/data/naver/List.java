
package com.grotesque.saa.player.data.naver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("useP2P")
    @Expose
    private Boolean useP2P;
    @SerializedName("duration")
    @Expose
    private Double duration;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("encodingOption")
    @Expose
    private EncodingOption encodingOption;
    @SerializedName("bitrate")
    @Expose
    private Bitrate bitrate;
    @SerializedName("p2pMetaUrl")
    @Expose
    private String p2pMetaUrl;
    @SerializedName("p2pUrl")
    @Expose
    private String p2pUrl;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("isDefault")
    @Expose
    private Boolean isDefault;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The useP2P
     */
    public Boolean getUseP2P() {
        return useP2P;
    }

    /**
     * 
     * @param useP2P
     *     The useP2P
     */
    public void setUseP2P(Boolean useP2P) {
        this.useP2P = useP2P;
    }

    /**
     * 
     * @return
     *     The duration
     */
    public Double getDuration() {
        return duration;
    }

    /**
     * 
     * @param duration
     *     The duration
     */
    public void setDuration(Double duration) {
        this.duration = duration;
    }

    /**
     * 
     * @return
     *     The size
     */
    public Integer getSize() {
        return size;
    }

    /**
     * 
     * @param size
     *     The size
     */
    public void setSize(Integer size) {
        this.size = size;
    }

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
     *     The encodingOption
     */
    public EncodingOption getEncodingOption() {
        return encodingOption;
    }

    /**
     * 
     * @param encodingOption
     *     The encodingOption
     */
    public void setEncodingOption(EncodingOption encodingOption) {
        this.encodingOption = encodingOption;
    }

    /**
     * 
     * @return
     *     The bitrate
     */
    public Bitrate getBitrate() {
        return bitrate;
    }

    /**
     * 
     * @param bitrate
     *     The bitrate
     */
    public void setBitrate(Bitrate bitrate) {
        this.bitrate = bitrate;
    }

    /**
     * 
     * @return
     *     The p2pMetaUrl
     */
    public String getP2pMetaUrl() {
        return p2pMetaUrl;
    }

    /**
     * 
     * @param p2pMetaUrl
     *     The p2pMetaUrl
     */
    public void setP2pMetaUrl(String p2pMetaUrl) {
        this.p2pMetaUrl = p2pMetaUrl;
    }

    /**
     * 
     * @return
     *     The p2pUrl
     */
    public String getP2pUrl() {
        return p2pUrl;
    }

    /**
     * 
     * @param p2pUrl
     *     The p2pUrl
     */
    public void setP2pUrl(String p2pUrl) {
        this.p2pUrl = p2pUrl;
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

    /**
     * 
     * @return
     *     The isDefault
     */
    public Boolean getIsDefault() {
        return isDefault;
    }

    /**
     * 
     * @param isDefault
     *     The isDefault
     */
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

}
