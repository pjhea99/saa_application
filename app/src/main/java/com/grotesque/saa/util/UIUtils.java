package com.grotesque.saa.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES10;
import android.opengl.GLES20;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.grotesque.saa.MyApplication;

import javax.microedition.khronos.egl.EGL10;

/**
 * Created by 경환 on 2016-04-23.
 */
public class UIUtils {
    private static int width = 0;
    private static int height = 0;

    private static void getDisplay(Context context) {
        if (width <= 0 || height <= 0) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            width = dm.widthPixels;
            height = dm.heightPixels;
        }
    }

    public static int getScreenWidth(Context context) {
        getDisplay(context);
        return width;
    }

    public static int getScreenHeight(Context context) {
        getDisplay(context);
        return height;
    }

    public static Drawable getRadialGradient(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
        float f = Math.min(paramInt1, paramInt2);
        GradientDrawable localGradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[] { paramInt3, paramInt4 });
        localGradientDrawable.setGradientType(1);
        localGradientDrawable.setGradientRadius(f / 2.0F);
        localGradientDrawable.setGradientCenter(0.5F, 0.5F);
        return localGradientDrawable;
    }


    @SuppressWarnings("deprecation")
    public static Point getScreenSize() {
        WindowManager window = (WindowManager) MyApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        Display display = window.getDefaultDisplay();
        Point screen = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(screen);
        } else {
            screen.set(display.getWidth(), display.getHeight());
        }
        return screen;
    }
    public static int getMaxTextureSize(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            EGLDisplay dpy = EGL14.eglGetDisplay(EGL14.EGL_DEFAULT_DISPLAY);
            int[] vers = new int[2];
            EGL14.eglInitialize(dpy, vers, 0, vers, 1);

            int[] configAttr = {
                    EGL14.EGL_COLOR_BUFFER_TYPE, EGL14.EGL_RGB_BUFFER,
                    EGL14.EGL_LEVEL, 0,
                    EGL14.EGL_RENDERABLE_TYPE, EGL14.EGL_OPENGL_ES2_BIT,
                    EGL14.EGL_SURFACE_TYPE, EGL14.EGL_PBUFFER_BIT,
                    EGL14.EGL_NONE
            };
            EGLConfig[] configs = new EGLConfig[1];
            int[] numConfig = new int[1];
            EGL14.eglChooseConfig(dpy, configAttr, 0,
                    configs, 0, 1, numConfig, 0);
            if (numConfig[0] == 0) {
                // TROUBLE! No config found.
            }
            EGLConfig config = configs[0];

            int[] surfAttr = {
                    EGL14.EGL_WIDTH, 64,
                    EGL14.EGL_HEIGHT, 64,
                    EGL14.EGL_NONE
            };
            EGLSurface surf = EGL14.eglCreatePbufferSurface(dpy, config, surfAttr, 0);

            int[] ctxAttrib = {
                    EGL14.EGL_CONTEXT_CLIENT_VERSION, 2,
                    EGL14.EGL_NONE
            };
            EGLContext ctx = EGL14.eglCreateContext(dpy, config, EGL14.EGL_NO_CONTEXT, ctxAttrib, 0);
            EGL14.eglMakeCurrent(dpy, surf, surf, ctx);
        }else{
            javax.microedition.khronos.egl.EGL10 egl = (javax.microedition.khronos.egl.EGL10)javax.microedition.khronos.egl.EGLContext.getEGL();

            javax.microedition.khronos.egl.EGLDisplay dpy = egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            int[] vers = new int[2];
            egl.eglInitialize(dpy, vers);

            int[] configAttr = {
                    javax.microedition.khronos.egl.EGL10.EGL_COLOR_BUFFER_TYPE, javax.microedition.khronos.egl.EGL10.EGL_RGB_BUFFER,
                    javax.microedition.khronos.egl.EGL10.EGL_LEVEL, 0,
                    javax.microedition.khronos.egl.EGL10.EGL_SURFACE_TYPE, javax.microedition.khronos.egl.EGL10.EGL_PBUFFER_BIT,
                    javax.microedition.khronos.egl.EGL10.EGL_NONE
            };
            javax.microedition.khronos.egl.EGLConfig[] configs = new javax.microedition.khronos.egl.EGLConfig[1];
            int[] numConfig = new int[1];
            egl.eglChooseConfig(dpy, configAttr, configs, 1, numConfig);
            if (numConfig[0] == 0) {
                // TROUBLE! No config found.
            }
            javax.microedition.khronos.egl.EGLConfig config = configs[0];

            int[] surfAttr = {
                    javax.microedition.khronos.egl.EGL10.EGL_WIDTH, 64,
                    javax.microedition.khronos.egl.EGL10.EGL_HEIGHT, 64,
                    javax.microedition.khronos.egl.EGL10.EGL_NONE
            };
            javax.microedition.khronos.egl.EGLSurface surf = egl.eglCreatePbufferSurface(dpy, config, surfAttr);
            final int EGL_CONTEXT_CLIENT_VERSION = 0x3098;  // missing in EGL10
            int[] ctxAttrib = {
                    EGL_CONTEXT_CLIENT_VERSION, 1,
                    javax.microedition.khronos.egl.EGL10.EGL_NONE
            };
            javax.microedition.khronos.egl.EGLContext ctx = egl.eglCreateContext(dpy, config, EGL10.EGL_NO_CONTEXT, ctxAttrib);
            egl.eglMakeCurrent(dpy, surf, surf, ctx);
            int[] maxSize = new int[1];
            GLES10.glGetIntegerv(GLES10.GL_MAX_TEXTURE_SIZE, maxSize, 0);
            egl.eglMakeCurrent(dpy, javax.microedition.khronos.egl.EGL10.EGL_NO_SURFACE, javax.microedition.khronos.egl.EGL10.EGL_NO_SURFACE,
                    EGL10.EGL_NO_CONTEXT);
            egl.eglDestroySurface(dpy, surf);
            egl.eglDestroyContext(dpy, ctx);
            egl.eglTerminate(dpy);
        }

        int[] maxTextureSize = new int[1];
        GLES20.glGetIntegerv(GLES20.GL_MAX_TEXTURE_SIZE, maxTextureSize, 0);
        return maxTextureSize[0];
    }
    public static int getCategoryColor(String category){
        switch (category){
            case "10282945":
                return Color.parseColor("#ffd92020");
            case "112817":
                return Color.parseColor("#e74c3c");
            case "112800":
                return Color.parseColor("#3498db");
            case "112806":
                return Color.parseColor("#34495e");
            case "34559678":
                return Color.parseColor("#2980b9");
            case "7904739":
                return Color.parseColor("#f1c40f");
            case "29559230":
                return Color.parseColor("#c0392b");
            case "22041632":
                return Color.parseColor("#f39c12");
            case "22999512":
                return Color.parseColor("#34495e");
            case "15939357":
                return Color.parseColor("#2c3e50");
            case "11719256":
                return Color.parseColor("#e74c3c");
            case "46192952":
                return Color.parseColor("#00a4ff");
            default:
                return Color.parseColor("#00c3bd");
        }
    }
}
