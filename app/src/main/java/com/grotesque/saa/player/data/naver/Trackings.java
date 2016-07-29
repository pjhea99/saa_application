
package com.grotesque.saa.player.data.naver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Trackings {

    @SerializedName("default")
    @Expose
    private List<Default> _default = new ArrayList<Default>();

    /**
     * 
     * @return
     *     The _default
     */
    public List<Default> getDefault() {
        return _default;
    }

    /**
     * 
     * @param _default
     *     The default
     */
    public void setDefault(List<Default> _default) {
        this._default = _default;
    }

}
