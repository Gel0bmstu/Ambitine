package com.example.networking.debugtools;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.View;
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

    public void debugAboveTheKeyboard(Activity activity, String text) {
        Toast toast = Toast.makeText(activity, text, Toast.LENGTH_SHORT);

        View toastView = toast.getView(); // This'll return the default View of the Toast.
        toastView.setPadding(16, 10, 16, 10);

        TextView toastMessage = toastView.findViewById(android.R.id.message);
        toastMessage.setTextColor(Color.WHITE);
        toastView.setBackgroundColor(activity.getResources().getColor(R.color.ambitine_primary_color));

        // determine position of visible view (without or with keyboard)
        Rect r = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);

        final float scale = activity.getResources().getDisplayMetrics().density;
        // check if keyboard activated
        if (Math.abs(r.bottom - activity.getWindow().getDecorView().getHeight()) < 150) {
            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, r.bottom - r.top - (int) (100 * scale - 0.5f));
        } else {
            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, r.bottom - r.top - (int) (50 * scale - 0.5f));
        }
        toast.show();
    }

    public void debugDarkColor(Activity activity, String text) {
        Toast toast = Toast.makeText(activity, text, Toast.LENGTH_SHORT);

        View toastView = toast.getView(); // This'll return the default View of the Toast.
        toastView.setPadding(16, 10, 16, 10);

        TextView toastMessage = toastView.findViewById(android.R.id.message);
        toastMessage.setTextColor(Color.WHITE);
        toastView.setBackgroundColor(activity.getResources().getColor(R.color.colorPrimaryDark));

        // determine position of visible view (without or with keyboard)
        Rect r = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);

        final float scale = activity.getResources().getDisplayMetrics().density;
        // check if keyboard activated
        if (Math.abs(r.bottom - activity.getWindow().getDecorView().getHeight()) < 150) {
            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, r.bottom - r.top - (int) (100 * scale - 0.5f));
        } else {
            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, r.bottom - r.top - (int) (50 * scale - 0.5f));
        }
        toast.show();
    }

    public void debugRedColor(Activity activity, String text) {
        Toast toast = Toast.makeText(activity, text, Toast.LENGTH_SHORT);

        View toastView = toast.getView(); // This'll return the default View of the Toast.
        toastView.setPadding(16, 10, 16, 10);

        TextView toastMessage = toastView.findViewById(android.R.id.message);
        toastMessage.setTextColor(Color.WHITE);
        toastView.setBackgroundColor(activity.getResources().getColor(R.color.declined_promises));

        // determine position of visible view (without or with keyboard)
        Rect r = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);

        final float scale = activity.getResources().getDisplayMetrics().density;
        // check if keyboard activated
        if (Math.abs(r.bottom - activity.getWindow().getDecorView().getHeight()) < 150) {
            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, r.bottom - r.top - (int) (100 * scale - 0.5f));
        } else {
            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, r.bottom - r.top - (int) (50 * scale - 0.5f));
        }
        toast.show();
    }
}
