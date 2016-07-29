package com.grotesque.saa.imageloader;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.target.Target;

import java.util.Locale;

public class MyProgressTarget<Z> extends ProgressTarget<String, Z> {
        private final TextView text;
        private final ImageView image;
        public MyProgressTarget(Target<Z> target, ImageView image, TextView text) {
            super(target);
            this.image = image;
            this.text = text;
        }


        @Override public float getGranualityPercentage() {
            return 0.1f; // this matches the format string for #text below
        }

        @Override protected void onConnecting() {
            image.setImageLevel(0);
            text.setVisibility(View.VISIBLE);
            text.setText("connecting");
        }
        @Override protected void onDownloading(long bytesRead, long expectedLength) {
            image.setImageLevel((int)(10000 * bytesRead / expectedLength));
            text.setText(String.format(Locale.US, "downloading %.2f/%.2f MB %.1f%%",
                    bytesRead / 1e6, expectedLength / 1e6, 100f * bytesRead / expectedLength));
        }
        @Override protected void onDownloaded() {
            image.setImageLevel(10000);
            text.setText("decoding and transforming");
        }
        @Override protected void onDelivered() {
            image.setImageLevel(0); // reset ImageView default
            text.setVisibility(View.INVISIBLE);
        }
}
