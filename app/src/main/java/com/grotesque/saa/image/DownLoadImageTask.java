package com.grotesque.saa.image;

import android.os.AsyncTask;
import android.os.Environment;
import android.webkit.MimeTypeMap;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 경환 on 2016-04-10.
 */
public class DownLoadImageTask extends AsyncTask<String, Void, Boolean> {
    private String mUrl;

    public DownLoadImageTask(String url) {
        mUrl = url;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        BufferedInputStream bis;
        try {
            String imageName = MimeTypeMap.getFileExtensionFromUrl(mUrl);
            imageName = (new StringBuilder()).append("saa").append(String.valueOf(System.currentTimeMillis())).append(".").append(imageName).toString();
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "SAA");
            if (!file.exists())
                file.mkdirs();
            File file1 = new File(file, imageName);
            URL imagUrl = new URL(mUrl);
            InputStream inputStream = imagUrl.openConnection().getInputStream();

            bis = new BufferedInputStream(inputStream);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[2048];
            int current = 0;

            while ((current = bis.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, current);
            }

            FileOutputStream fos = new FileOutputStream(file1);
            fos.write(buffer.toByteArray());
            fos.close();

            bis.close();
            inputStream.close();


            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
