package mobile.com.mobilephonebuyers.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

public interface BasePresenter {

    public void onSaveInstanceState(@NonNull Bundle outState);
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState);
    public void onDestroy();
    public void onDestroyView();

}
