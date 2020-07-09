package com.example.listenapp.custom;

import com.google.android.material.snackbar.Snackbar;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class CustomSnackbar {

    public CustomSnackbar() {
    }

    public Snackbar makeSB(View v, String msg){
        return Snackbar.make(v,msg, Snackbar.LENGTH_LONG);
    }
    public Snackbar makeSB(View v, String msg, int side, int bottom){
        Snackbar snackbar = Snackbar.make(v,msg, Snackbar.LENGTH_LONG);
        final View snackBarView = snackbar.getView();
        final CoordinatorLayout.LayoutParams params =
                (CoordinatorLayout.LayoutParams) snackBarView.getLayoutParams();
        params.setMargins(params.leftMargin + side,
                params.topMargin,
                params.rightMargin + side,
                params.bottomMargin +bottom);
        snackBarView.setLayoutParams(params);
        return snackbar;
    }
}

