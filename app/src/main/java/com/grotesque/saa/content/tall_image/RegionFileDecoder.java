package com.grotesque.saa.content.tall_image;

import android.content.Context;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;

import java.io.File;
import java.io.IOException;

class RegionFileDecoder extends RegionResourceDecoder<File> {
	public RegionFileDecoder(Context context, Rect region) {
		super(context, region);
	}

	@Override protected BitmapRegionDecoder createDecoder(File source, int width, int height) throws IOException {
		return BitmapRegionDecoder.newInstance(source.getAbsolutePath(), false);
	}
}
