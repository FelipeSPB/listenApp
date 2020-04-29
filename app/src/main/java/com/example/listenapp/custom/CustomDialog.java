package com.example.listenapp.custom;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

import com.example.listenapp.R;

public class CustomDialog extends AppCompatDialogFragment {

    Context context;
    String title, msg;

    public CustomDialog(Context context, String title, String msg) {
        this.context = context;
        this.title = title;
        this.msg = msg;
    }
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context).setTitle(title).setMessage(msg);
        dialog.setPositiveButton(getString(R.string.Answer_yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Activity activity = (Activity) context;
                activity.finish();
            }
        });
        dialog.setNegativeButton(getString(R.string.Answer_no), null);
        return dialog.create();
    }

}
