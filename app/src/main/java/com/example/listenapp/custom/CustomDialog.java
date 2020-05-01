package com.example.listenapp.custom;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;



public class CustomDialog extends AlertDialog.Builder {

    private Context context;


    public CustomDialog(Context context, String title, String msg, String positive, String negative) {
        super(context);
        this.context = context;
        setTitle(title);
        setMessage(msg);
        setButtons(positive,negative);
        create();

    }
    public void setButtons(String yes, String no){
        setPositiveButton(yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Activity activity = (Activity) context;
                activity.finish();
            }
        });
        setNegativeButton(no, null);
    }
}
