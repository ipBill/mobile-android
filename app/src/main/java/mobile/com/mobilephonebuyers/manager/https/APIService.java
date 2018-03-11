package mobile.com.mobilephonebuyers.manager.https;

import com.google.gson.JsonObject;

import java.util.List;

import mobile.com.mobilephonebuyers.mobile_detail.dao.MobileDetailObject;
import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by bill on 11/3/2018 AD.
 */

public interface APIService {

    @GET("mobiles")
    Call<List<MobileObject>> getMobileList();

    @GET("mobiles/{mobileId}/images")
    Call<List<MobileDetailObject>> getMobileDetailImage(@Path("mobileId") int mobileId);

}
