package mobile.com.mobilephonebuyers.manager.https;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by bill on 11/3/2018 AD.
 */

public interface APIService {

    @GET("mobiles")
    Call<JsonObject> getMobileList();

}
