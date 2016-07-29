package com.grotesque.saa.player.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntegratedMovieData extends MovieData {
    private Location location;
    private ArrayList<VideoProfileBean> profileList;
    private Thumbnail thumbnail;
    private Tracking tracking;
    private XylophoneReq xylophone_req;

    public IntegratedMovieData.Location getLocation()
    {
      return this.location;
    }

    public ArrayList<VideoProfileBean> getProfileList() {
        if (this.profileList == null) {
            this.profileList = new ArrayList();
            for (Output output : getOutput_list().getOutput_list()) {
                VideoProfileBean videoProfileBean = new VideoProfileBean(output.getProfile(), output.getLabel(), output.getDuration(), output.getFilesize());
                if (videoProfileBean.getDuration() > 0) {
                    this.profileList.add(videoProfileBean);
                }
             }
            Collections.sort(this.profileList, new VideoProfileBean());
        }
        return this.profileList;
    }
  
  public Thumbnail getThumbnail()
  {
    return this.thumbnail;
  }
  
  public String getThumbnailUrl()
  {
    if ((this.thumbnail != null) && (this.thumbnail.getMain() != null)) {
      return this.thumbnail.getMain().getUrl();
    }
    return "";
  }
  
  public IntegratedMovieData.Tracking getTracking()
  {
    return this.tracking;
  }
  
  public XylophoneReq getXylophone_req()
  {
    return this.xylophone_req;
  }
  
  public void setLocation(IntegratedMovieData.Location paramLocation)
  {
    this.location = paramLocation;
  }
  
  public void setOutput_list(OutputList paramOutputList)
  {
    super.setOutput_list(paramOutputList);
  }
  
  public void setThumbnail(IntegratedMovieData.Thumbnail paramThumbnail)
  {
    this.thumbnail = paramThumbnail;
  }
  
  public void setTracking(IntegratedMovieData.Tracking paramTracking)
  {
    this.tracking = paramTracking;
  }
  
  public void setXylophone_req(XylophoneReq paramXylophoneReq)
  {
    this.xylophone_req = paramXylophoneReq;
  }
  class Location
  {
    String url;

    public String getUrl()
    {
      return this.url;
    }

    public void setUrl(String paramString)
    {
      this.url = paramString;
    }
  }
  class Thumbnail {
    ThumbnailItem main;

    public ThumbnailItem getMain() {
      return this.main;
    }

    public void setMain(ThumbnailItem paramThumbnailItem)  {
      this.main = paramThumbnailItem;
    }
  }
  class ThumbnailItem {
    String url;

    public String getUrl() {
      return this.url;
    }

    public void setUrl(String paramString) {
      this.url = paramString;
    }
  }
  class Tracking {
    int count;
    List<TrackingItem> tracking;

    public int getCount()
    {
      return this.count;
    }

    public List<TrackingItem> getTracking()
    {
      return this.tracking;
    }

    class TrackingItem {
      public static final String TYPE_OFFSET = "offset";
      public static final String TYPE_RUNNING = "running_time";
      int sec;
      String type;
      String url;

    public int getSec() {
      return this.sec;
    }

    public String getType() {
      return this.type;
    }

    public String getUrl() {
      return this.url;
    }

    public void setSec(int paramInt) {
      this.sec = paramInt;
    }

    public void setType(String paramString) {
      this.type = paramString;
    }

    public void setUrl(String paramString) {
      this.url = paramString;
    }
  }
  }

}