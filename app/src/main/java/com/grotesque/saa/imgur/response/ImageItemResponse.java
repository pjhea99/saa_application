package com.grotesque.saa.imgur.response;

import com.grotesque.saa.imgur.data.ImageItem;

public class ImageItemResponse {

  private ImageItem data;
  
  public ImageItem getData() {
    return this.data;
  }
  
  public void setData(ImageItem paramImageItem) {
    this.data = paramImageItem;
  }
}