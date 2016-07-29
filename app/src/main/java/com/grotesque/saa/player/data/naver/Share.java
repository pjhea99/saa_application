
package com.grotesque.saa.player.data.naver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Share {

    @SerializedName("usable")
    @Expose
    private Boolean usable;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("onlyInnerServices")
    @Expose
    private Boolean onlyInnerServices;

    /**
     * 
     * @return
     *     The usable
     */
    public Boolean getUsable() {
        return usable;
    }

    /**
     * 
     * @param usable
     *     The usable
     */
    public void setUsable(Boolean usable) {
        this.usable = usable;
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
     *     The onlyInnerServices
     */
    public Boolean getOnlyInnerServices() {
        return onlyInnerServices;
    }

    /**
     * 
     * @param onlyInnerServices
     *     The onlyInnerServices
     */
    public void setOnlyInnerServices(Boolean onlyInnerServices) {
        this.onlyInnerServices = onlyInnerServices;
    }

}
