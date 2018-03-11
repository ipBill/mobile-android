package mobile.com.mobilephonebuyers.mobile_detail.interactor;

import android.support.annotation.NonNull;

import java.util.List;

import mobile.com.mobilephonebuyers.manager.HttpServiceManager;
import mobile.com.mobilephonebuyers.manager.https.APIService;
import mobile.com.mobilephonebuyers.mobile_detail.dao.MobileDetailObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.HTTP;

/**
 * Created by bill on 12/3/2018 AD.
 */

public class MobileDetailFragmentInteractor implements IMobileDetailFragmentInteractor {
    @Override
    public void loadMobileDetailImageFromService(int id, final MobileDetailFragmentInteractorListener listener) {
        APIService service = HttpServiceManager.getInstance().getService();
        service.getMobileDetailImage(id).enqueue(new Callback<List<MobileDetailObject>>() {
            @Override
            public void onResponse(@NonNull Call<List<MobileDetailObject>> call, @NonNull Response<List<MobileDetailObject>> response) {
                if (response.isSuccessful()) {
                    listener.loadMobileDetailImageFromServiceFinished(true, response.body());
                } else {
                    listener.loadMobileDetailImageFromServiceFinished(false, null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<MobileDetailObject>> call, @NonNull Throwable t) {
                t.printStackTrace();
                listener.loadMobileDetailImageFromServiceFinished(false, null);
            }
        });
    }
}
