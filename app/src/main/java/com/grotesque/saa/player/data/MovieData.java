package com.grotesque.saa.player.data;

import android.text.TextUtils;

import java.util.List;

public class MovieData{

    public MovieData()
    {
    }

    public Ads getAds()
    {
        return ads;
    }

    public OutputList getOutput_list()
    {
        return output_list;
    }

    public String getOwnerid()
    {
        return ownerid;
    }

    public String getSecid()
    {
        if(ads != null && ads.getPreroll() != null)
            return ads.getPreroll().getSecid();
        else
            return "";
    }

    public String getSmrToken()
    {
        if(ads != null)
            return ads.getToken();
        else
            return "";
    }

    public String getStatus()
    {
        return status;
    }

    public String getSvcname()
    {
        return svcname;
    }

    public String getTid()
    {
        return tid;
    }

    public String getUuid()
    {
        return uuid;
    }

    public String getVid()
    {
        return vid;
    }

    public boolean hasAd()
    {
        return ads != null && (ads.getPreroll() != null || !TextUtils.isEmpty(ads.getToken()));
    }

    public boolean isIs_youth_pest_video()
    {
        return is_youth_pest_video;
    }

    public boolean isShould_protect_contents()
    {
        return should_protect_contents;
    }

    public boolean is_geo_block()
    {
        return is_geo_block;
    }

    public boolean is_tvpot_post()
    {
        return is_tvpot_post;
    }

    public boolean is_youth_pest_video()
    {
        return is_youth_pest_video;
    }

    public void setAds(Ads ads1)
    {
        ads = ads1;
    }

    public void setIs_geo_block(boolean flag)
    {
        is_geo_block = flag;
    }

    public void setIs_tvpot_post(boolean flag)
    {
        is_tvpot_post = flag;
    }

    public void setIs_youth_pest_video(boolean flag)
    {
        is_youth_pest_video = flag;
    }

    public void setOutput_list(OutputList outputlist)
    {
        output_list = outputlist;
    }

    public void setOwnerid(String s)
    {
        ownerid = s;
    }

    public void setShould_protect_contents(boolean flag)
    {
        should_protect_contents = flag;
    }

    public void setStatus(String s)
    {
        status = s;
    }

    public void setSvcname(String s)
    {
        svcname = s;
    }

    public void setTid(String s)
    {
        tid = s;
    }

    public void setUuid(String s)
    {
        uuid = s;
    }

    public void setVid(String s)
    {
        vid = s;
    }

    private Ads ads;
    private boolean is_geo_block;
    private boolean is_tvpot_post;
    private boolean is_youth_pest_video;
    private OutputList output_list;
    private String ownerid;
    private boolean should_protect_contents;
    private String status;
    private String svcname;
    private String tid;
    private String uuid;
    private String vid;

    private class Ads
    {

        public Preroll getPreroll()
        {
            return preroll;
        }

        public String getProvider()
        {
            return provider;
        }

        public String getToken()
        {
            return token;
        }

        public void setPreroll(Preroll preroll1)
        {
            preroll = preroll1;
        }

        public void setProvider(String s)
        {
            provider = s;
        }

        public void setToken(String s)
        {
            token = s;
        }

        Preroll preroll;
        String provider;
        String token;

        public Ads()
        {
        }
    }


    private class Preroll
    {

        public String getAd_play_type()
        {
            return ad_play_type;
        }

        public String getFrequency()
        {
            return frequency;
        }

        public String getSecid()
        {
            return secid;
        }

        public void setAd_play_type(String s)
        {
            ad_play_type = s;
        }

        public void setFrequency(String s)
        {
            frequency = s;
        }

        public void setSecid(String s)
        {
            secid = s;
        }

        String ad_play_type;
        String frequency;
        String secid;

        public Preroll()
        {
        }
    }
    public class OutputList
    {
        List<Output> output_list;

        public List<Output> getOutput_list()
        {
            return this.output_list;
        }

        public void setOutput_list(List<Output> paramList)
        {
            this.output_list = paramList;
        }
    }
    public class Output
    {
        int duration;
        int filesize;
        int height;
        String label;
        String preset;
        String profile;
        String state;
        int width;

        public int getDuration()
        {
            return this.duration;
        }

        public int getFilesize()
        {
            return this.filesize;
        }

        public int getHeight()
        {
            return this.height;
        }

        public String getLabel()
        {
            return this.label;
        }

        public String getPreset()
        {
            return this.preset;
        }

        public String getProfile()
        {
            return this.profile;
        }

        public String getState()
        {
            return this.state;
        }

        public int getWidth()
        {
            return this.width;
        }

        public void setDuration(int paramInt)
        {
            this.duration = paramInt;
        }

        public void setFilesize(int paramInt)
        {
            this.filesize = paramInt;
        }

        public void setHeight(int paramInt)
        {
            this.height = paramInt;
        }

        public void setLabel(String paramString)
        {
            this.label = paramString;
        }

        public void setPreset(String paramString)
        {
            this.preset = paramString;
        }

        public void setProfile(String paramString)
        {
            this.profile = paramString;
        }

        public void setState(String paramString)
        {
            this.state = paramString;
        }

        public void setWidth(int paramInt)
        {
            this.width = paramInt;
        }
    }

}
