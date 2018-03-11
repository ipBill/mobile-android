package mobile.com.mobilephonebuyers;

import android.app.Application;

import mobile.com.mobilephonebuyers.manager.Contextor;

/**
 * Created by bill on 11/3/2018 AD.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
