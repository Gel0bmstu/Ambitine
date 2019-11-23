package com.example.networking;

import android.app.Application;

public class AmbitineApplication extends Application {
    private Boolean internetIsAvailable;

    public void setInternetavAilability(boolean internetAvailability) {
        this.internetIsAvailable = internetAvailability;
    }

    public Boolean getInternetavAilability() {
        return internetIsAvailable;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
