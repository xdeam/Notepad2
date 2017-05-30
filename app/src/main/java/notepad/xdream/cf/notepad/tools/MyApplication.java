package notepad.xdream.cf.notepad.tools;

import android.app.Application;
import android.content.Context;

/**
 * Created by kwinter on 2017/5/26.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
