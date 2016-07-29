
package com.grotesque.saa.player.data.naver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rules {

    @SerializedName("splitVod")
    @Expose
    private SplitVod splitVod;
    @SerializedName("preloadingMidAd")
    @Expose
    private PreloadingMidAd preloadingMidAd;
    @SerializedName("adFreeZone")
    @Expose
    private AdFreeZone adFreeZone;

    /**
     * 
     * @return
     *     The splitVod
     */
    public SplitVod getSplitVod() {
        return splitVod;
    }

    /**
     * 
     * @param splitVod
     *     The splitVod
     */
    public void setSplitVod(SplitVod splitVod) {
        this.splitVod = splitVod;
    }

    /**
     * 
     * @return
     *     The preloadingMidAd
     */
    public PreloadingMidAd getPreloadingMidAd() {
        return preloadingMidAd;
    }

    /**
     * 
     * @param preloadingMidAd
     *     The preloadingMidAd
     */
    public void setPreloadingMidAd(PreloadingMidAd preloadingMidAd) {
        this.preloadingMidAd = preloadingMidAd;
    }

    /**
     * 
     * @return
     *     The adFreeZone
     */
    public AdFreeZone getAdFreeZone() {
        return adFreeZone;
    }

    /**
     * 
     * @param adFreeZone
     *     The adFreeZone
     */
    public void setAdFreeZone(AdFreeZone adFreeZone) {
        this.adFreeZone = adFreeZone;
    }

}
