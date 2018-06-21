package com.example.huntertsai.dictionary_app;

/**
 * Created by huntertsai on 2018-01-19.
 */

import android.app.Application;

public class SampleApp extends Application {
    private ApiClient apiClient;

    @Override
    public void onCreate() {
        super.onCreate();

        apiClient = new ApiClient();
    }

    public ApiClient apiClient() {
        return apiClient;
    }
}
