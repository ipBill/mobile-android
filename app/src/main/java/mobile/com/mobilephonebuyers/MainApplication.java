package mobile.com.mobilephonebuyers;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import mobile.com.mobilephonebuyers.manager.Contextor;

/**
 * Created by bill on 11/3/2018 AD.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("mobile.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
