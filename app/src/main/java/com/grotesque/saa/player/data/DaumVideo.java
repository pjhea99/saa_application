package com.grotesque.saa.player.data;

import com.google.gson.annotations.SerializedName;

public class DaumVideo {
  @SerializedName("type")
  private String type;
  @SerializedName("profile")
  private String profile;
  @SerializedName("url")
  private String url;
  
  public String getType()
  {
    return this.type;
  }
  
  public String getProfile()
  {
    return this.profile;
  }
  
  public String getUrl()
  {
    return this.url;
  }
  
  public void setType(String paramString)
  {
    this.type = paramString;
  }
  
  public void setProfile(String paramString)
  {
    this.profile = paramString;
  }
  
  public void setUrl(String paramString)
  {
    this.url = paramString;
  }
}