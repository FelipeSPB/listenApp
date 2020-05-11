<<<<<<< HEAD:app/src/main/java/com/example/listenapp/custom/CustomSnackbar.java
package com.example.listenapp.custom;
=======
package com.example.listenapp.main;
>>>>>>> Giulia:app/src/main/java/com/example/listenapp/main/CustomSnackbar.java

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;

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

