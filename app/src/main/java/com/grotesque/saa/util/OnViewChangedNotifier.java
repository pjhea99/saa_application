package com.grotesque.saa.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class OnViewChangedNotifier
{
  private static OnViewChangedNotifier a;
  private final List<OnViewChangedListener> b = new LinkedList();
  
  public static void registerOnViewChangedListener(OnViewChangedListener paramOnViewChangedListener)
  {
    if (a != null) {
      a.b.add(paramOnViewChangedListener);
    }
  }
  
  public static OnViewChangedNotifier replaceNotifier(OnViewChangedNotifier paramOnViewChangedNotifier)
  {
    OnViewChangedNotifier localOnViewChangedNotifier = a;
    a = paramOnViewChangedNotifier;
    return localOnViewChangedNotifier;
  }
  
  public void notifyViewChanged(HasViews paramHasViews)
  {
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext()) {
      ((OnViewChangedListener)localIterator.next()).onViewChanged(paramHasViews);
    }
  }

  public abstract interface OnViewChangedListener
  {
    public abstract void onViewChanged(HasViews paramHasViews);
  }
}
