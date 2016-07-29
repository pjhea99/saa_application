
package com.grotesque.saa.player.data.naver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Meta {

    @SerializedName("masterVideoId")
    @Expose
    private String masterVideoId;
    @SerializedName("contentId")
    @Expose
    private String contentId;
    @SerializedName("serviceId")
    @Expose
    private Integer serviceId;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("interfaceLang")
    @Expose
    private String interfaceLang;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("homeUrl")
    @Expose
    private String homeUrl;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("cover")
    @Expose
    private Cover cover;
    @SerializedName("advertiseInfo")
    @Expose
    private String advertiseInfo;
    @SerializedName("advertiseUrl")
    @Expose
    private String advertiseUrl;
    @SerializedName("share")
    @Expose
    private Share share;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("relationVideo")
    @Expose
    private RelationVideo relationVideo;
    @SerializedName("apiList")
    @Expose
    private List<ApiList> apiList = new ArrayList<ApiList>();
    @SerializedName("display")
    @Expose
    private Display display;
    @SerializedName("rules")
    @Expose
    private Rules rules;

    /**
     * 
     * @return
     *     The masterVideoId
     */
    public String getMasterVideoId() {
        return masterVideoId;
    }

    /**
     * 
     * @param masterVideoId
     *     The masterVideoId
     */
    public void setMasterVideoId(String masterVideoId) {
        this.masterVideoId = masterVideoId;
    }

    /**
     * 
     * @return
     *     The contentId
     */
    public String getContentId() {
        return contentId;
    }

    /**
     * 
     * @param contentId
     *     The contentId
     */
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    /**
     * 
     * @return
     *     The serviceId
     */
    public Integer getServiceId() {
        return serviceId;
    }

    /**
     * 
     * @param serviceId
     *     The serviceId
     */
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * 
     * @return
     *     The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *     The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 
     * @return
     *     The interfaceLang
     */
    public String getInterfaceLang() {
        return interfaceLang;
    }

    /**
     * 
     * @param interfaceLang
     *     The interfaceLang
     */
    public void setInterfaceLang(String interfaceLang) {
        this.interfaceLang = interfaceLang;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The homeUrl
     */
    public String getHomeUrl() {
        return homeUrl;
    }

    /**
     * 
     * @param homeUrl
     *     The homeUrl
     */
    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }

    /**
     * 
     * @return
     *     The subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 
     * @param subject
     *     The subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * 
     * @return
     *     The cover
     */
    public Cover getCover() {
        return cover;
    }

    /**
     * 
     * @param cover
     *     The cover
     */
    public void setCover(Cover cover) {
        this.cover = cover;
    }

    /**
     * 
     * @return
     *     The advertiseInfo
     */
    public String getAdvertiseInfo() {
        return advertiseInfo;
    }

    /**
     * 
     * @param advertiseInfo
     *     The advertiseInfo
     */
    public void setAdvertiseInfo(String advertiseInfo) {
        this.advertiseInfo = advertiseInfo;
    }

    /**
     * 
     * @return
     *     The advertiseUrl
     */
    public String getAdvertiseUrl() {
        return advertiseUrl;
    }

    /**
     * 
     * @param advertiseUrl
     *     The advertiseUrl
     */
    public void setAdvertiseUrl(String advertiseUrl) {
        this.advertiseUrl = advertiseUrl;
    }

    /**
     * 
     * @return
     *     The share
     */
    public Share getShare() {
        return share;
    }

    /**
     * 
     * @param share
     *     The share
     */
    public void setShare(Share share) {
        this.share = share;
    }

    /**
     * 
     * @return
     *     The user
     */
    public User getUser() {
        return user;
    }

    /**
     * 
     * @param user
     *     The user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 
     * @return
     *     The relationVideo
     */
    public RelationVideo getRelationVideo() {
        return relationVideo;
    }

    /**
     * 
     * @param relationVideo
     *     The relationVideo
     */
    public void setRelationVideo(RelationVideo relationVideo) {
        this.relationVideo = relationVideo;
    }

    /**
     * 
     * @return
     *     The apiList
     */
    public List<ApiList> getApiList() {
        return apiList;
    }

    /**
     * 
     * @param apiList
     *     The apiList
     */
    public void setApiList(List<ApiList> apiList) {
        this.apiList = apiList;
    }

    /**
     * 
     * @return
     *     The display
     */
    public Display getDisplay() {
        return display;
    }

    /**
     * 
     * @param display
     *     The display
     */
    public void setDisplay(Display display) {
        this.display = display;
    }

    /**
     * 
     * @return
     *     The rules
     */
    public Rules getRules() {
        return rules;
    }

    /**
     * 
     * @param rules
     *     The rules
     */
    public void setRules(Rules rules) {
        this.rules = rules;
    }

}
