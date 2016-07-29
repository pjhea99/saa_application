package com.grotesque.saa.util;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class SoftInputUtils
{
  public static ResultReceiver NOP = new ResultReceiver(null);
  private static final int a = 100;
  
  public static InputMethodManager getInputMethodManager(Context paramContext)
  {
    return (InputMethodManager)paramContext.getSystemService(Context.INPUT_METHOD_SERVICE);
  }
  
  public static boolean hide(View view)
  {
    return hide(view, NOP);
  }
  
  public static boolean hide(View view, ResultReceiver resultReceiver) {
    InputMethodManager inputMethodManager = getInputMethodManager(view.getContext());
    boolean flag = false;
    if (inputMethodManager != null)  {
      flag = inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0, resultReceiver);
    }
    return flag;
  }
  
  public static boolean hideAndWait(View view) {
    SoftInputUtils.BlockingResultReceiver blockingResultReceiver = new SoftInputUtils.BlockingResultReceiver();
    if (hide(view, blockingResultReceiver))
    {
      int i = blockingResultReceiver.getResultCode();
      return (i == 3) || (i == 1);
    }
    return isShown(view);
  }
  
  public static boolean isShown(View view) {
    if(view != null) {
      Rect rect = new Rect();
      view.getWindowVisibleDisplayFrame(rect);
      if(view.getRootView().getHeight() - rect.height() > 100)
        return true;
    }
    return false;
  }
  
  public static boolean show(View view)
  {
    return show(view, NOP);
  }
  
  public static boolean show(View view, ResultReceiver paramResultReceiver)  {

    InputMethodManager inputMethodManager = getInputMethodManager(view.getContext());
    boolean flag = false;
    if (inputMethodManager != null)
    {
      flag = false;
      if (view.requestFocus())
      {
        flag = inputMethodManager.showSoftInput(view, 0, paramResultReceiver);
      }
    }
    return flag;
  }
  
  public static boolean showAndWait(View view)
  {
    SoftInputUtils.BlockingResultReceiver localBlockingResultReceiver = new SoftInputUtils.BlockingResultReceiver();
    if (show(view, localBlockingResultReceiver))
    {
      int i = localBlockingResultReceiver.getResultCode();
      return (i == 2) || (i == 0);
    }
    return isShown(view);
  }

  static class BlockingResultReceiver extends ResultReceiver  {

    public int getResultCode()
    {
      if(d == -1) {
        try {
          wait(c);
        } catch (InterruptedException interruptedexception) {
        }

      }
      return d;
    }

    protected void onReceiveResult(int i, Bundle bundle)
    {
      d = i;
      try
      {
        notifyAll();
        return;
      }
      // Misplaced declaration of an exception variable
      catch(Exception e)
      {
        return;
      }
    }

    private static final long a = 1000L;
    private static final int b = -1;
    private final long c;
    private int d;

    public BlockingResultReceiver()
    {
      this(null, 1000L);
    }

    public BlockingResultReceiver(Handler handler)
    {
      this(handler, 1000L);
    }

    public BlockingResultReceiver(Handler handler, long l)
    {
      super(handler);
      d = -1;
      c = l;
    }
  }

}