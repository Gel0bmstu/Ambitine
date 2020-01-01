package com.example.networking.debugtools;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;

import com.example.networking.R;

public class AmbitinedToast {

    private static AmbitinedToast atInstance = null;

    public static AmbitinedToast getInstance() {
        if (atInstance == null)
            atInstance = new AmbitinedToast();

        return atInstance;
    }


    public void debug(Context activityContext, String text) {
        Toast toast = Toast.makeText(activityContext, text, Toast.LENGTH_SHORT);
        View toastView = toast.getView(); // This'll return the default View of the Toast.
        TextView toastMessage = toastView.findViewById(android.R.id.message);
        toastMessage.setTextColor(Color.WHITE);
        toastView.setBackgroundColor(activityContext.getResources().getColor(R.color.ambitine_primary_color));
        toast.show();
    }

    public void debugAboveTheKeyboard(Activity activity, String text) {
        Toast toast = Toast.makeText(activity, text, Toast.LENGTH_SHORT);

        View toastView = toast.getView(); // This'll return the default View of the Toast.
        toastView.setPadding(16, 10, 16, 10);


        TextView toastMessage = toastView.findViewById(android.R.id.message);
        toastMessage.setTextColor(Color.WHITE);
        toastView.setBackgroundColor(activity.getResources().getColor(R.color.ambitine_primary_color));
        int toastHeight = toastMessage.getHeight();

        // determine position of visible view (without or with keyboard)
        Rect r = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);

        toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, r.bottom - r.top - toastHeight);
        toast.show();

        System.out.println(toast.getView().findViewById(android.R.id.message).getHeight() + " : hui");
    }
}
