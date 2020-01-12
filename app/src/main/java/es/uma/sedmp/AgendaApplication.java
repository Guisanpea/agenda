package es.uma.sedmp;

import android.app.Application;
import android.content.Context;

public class AgendaApplication extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        AgendaApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return AgendaApplication.context;
    }
}
