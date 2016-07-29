package com.grotesque.saa.player.data;

import java.util.Comparator;

public class VideoProfileBean
  implements Comparator<VideoProfileBean>
{
  public static final String DEFAULT_PROFILE = "BASE";
  private float bitrate;
  private int duration;
  private long filesize;
  private boolean isLocalFile;
  private String label;
  private String name;
  
  public VideoProfileBean()
  {
    this.name = "BASE";
    this.label = getLabel(this.name);
  }
  
  public VideoProfileBean(String paramString1, String paramString2, int paramInt, long paramLong)
  {
    this.name = paramString1;
    this.label = paramString2;
    this.duration = paramInt;
    this.filesize = paramLong;
    this.bitrate = ((float)paramLong / 1024.0F / paramInt);
  }
  
  public static String getLabel(String paramString)
  {
    if ("LOW".equals(paramString)) {
      return "240P";
    }
    if ("BASE".equals(paramString)) {
      return "360P";
    }
    if ("MAIN".equals(paramString)) {
      return "360P+";
    }
    if ("HIGH".equals(paramString)) {
      return "720P";
    }
    if ("HIGH4".equals(paramString)) {
      return "1080P";
    }
    return "";
  }
  
  public static int getOrdering(String paramString)
  {
    if ("LOW".equals(paramString)) {
      return 1;
    }
    if ("BASE".equals(paramString)) {
      return 2;
    }
    if ("MAIN".equals(paramString)) {
      return 3;
    }
    if ("HIGH".equals(paramString)) {
      return 4;
    }
    if ("HIGH4".equals(paramString)) {
      return 5;
    }
    return -1;
  }
  
  public int compare(VideoProfileBean paramVideoProfileBean1, VideoProfileBean paramVideoProfileBean2)
  {
    if (paramVideoProfileBean1.bitrate > paramVideoProfileBean2.bitrate) {
      return 1;
    }
    if (paramVideoProfileBean1.bitrate < paramVideoProfileBean2.bitrate) {
      return -1;
    }
    return 0;
  }
  
  public boolean equals(String paramString)
  {
    return this.name.equalsIgnoreCase(paramString);
  }
  
  public boolean equals(VideoProfileBean paramVideoProfileBean)
  {
    return this.name.equalsIgnoreCase(paramVideoProfileBean.getName());
  }
  
  public float getBitrate()
  {
    return this.bitrate;
  }
  
  public int getDuration()
  {
    return this.duration;
  }
  
  public long getFilesize()
  {
    return this.filesize;
  }
  
  public String getLabel()
  {
    return this.label;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getShortLabel()
  {
    return this.label.split(" ")[0];
  }
  
  public boolean isLocalFile()
  {
    return this.isLocalFile;
  }
  
  public void setDuration(int paramInt)
  {
    this.duration = paramInt;
  }
  
  public void setFilesize(long paramLong)
  {
    this.filesize = paramLong;
  }
  
  public void setLabel(String paramString)
  {
    this.label = paramString;
  }
  
  public void setLocalFile(boolean paramBoolean)
  {
    this.isLocalFile = paramBoolean;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
}