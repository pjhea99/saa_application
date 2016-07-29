package com.grotesque.saa.common.widget;

import android.app.AlertDialog;
import android.content.Context;

public class CustomAlertDialog extends AlertDialog.Builder{
    public CustomAlertDialog(Context paramContext)  {
        super(paramContext);
    }

    public CustomAlertDialog(Context paramContext, int paramInt)  {
        super(paramContext, paramInt);
    }

    public AlertDialog show()  {
        AlertDialog localAlertDialog = create();
        localAlertDialog.setCanceledOnTouchOutside(true);
        localAlertDialog.show();
        return localAlertDialog;
    }
}