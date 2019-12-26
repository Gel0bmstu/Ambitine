package com.example.networking.debugtools;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.networking.R;

public class AmbitinedToast {

    private static AmbitinedToast atInstance = null;

    public static AmbitinedToast getInstance()
    {
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
}
