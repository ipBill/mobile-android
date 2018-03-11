package mobile.com.mobilephonebuyers.manager;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {

    private static SharedPreferenceManager instance;
    private SharedPreferences preferences;

    public static SharedPreferenceManager getInstance() {
        if (instance == null)
            instance = new SharedPreferenceManager();
        return instance;
    }


    private SharedPreferenceManager() {
        Context mContext = Contextor.getInstance().getContext();
        preferences = mContext.getSharedPreferences("mobile", Context.MODE_PRIVATE);
    }


    public void setSortId(int sortId) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("sortId", sortId);
        editor.apply();
    }

    public int getSortId() {
        return preferences.getInt("sortId", 0);
    }

}
