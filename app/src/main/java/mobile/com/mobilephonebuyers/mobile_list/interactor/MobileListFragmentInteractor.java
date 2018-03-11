package mobile.com.mobilephonebuyers.mobile_list.interactor;

import android.support.annotation.NonNull;

import java.util.List;

import mobile.com.mobilephonebuyers.manager.HttpServiceManager;
import mobile.com.mobilephonebuyers.manager.https.APIService;
import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bill on 11/3/2018 AD.
 */

public class MobileListFragmentInteractor implements IMobileListFragmentInteractor {

    @Override
    public void loadMobileListFromService(final int sortId, final LoadMobileListListener listener) {
        APIService service = HttpServiceManager.getInstance().getService();

        service.getMobileList().enqueue(new Callback<List<MobileObject>>() {
            @Override
            public void onResponse(@NonNull Call<List<MobileObject>> call, @NonNull Response<List<MobileObject>> response) {
                if (response.isSuccessful()) {
                    listener.loadMobileListFromServiceFinished(true, sortId, response.body());
                } else {
                    listener.loadMobileListFromServiceFinished(false, sortId, null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<MobileObject>> call, @NonNull Throwable t) {
                t.printStackTrace();
                listener.loadMobileListFromServiceFinished(false, sortId, null);
            }
        });
    }
}
