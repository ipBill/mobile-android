package mobile.com.mobilephonebuyers.mobile_detail.dao;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by bill on 12/3/2018 AD.
 */

@Parcel
public class MobileDetailObject {

    @SerializedName("id")
    int id;

    @SerializedName("mobile_id")
    int mobileId;

    @SerializedName("url")
    String url;

    public int getId() {
        return id;
    }

    public int getMobileId() {
        return mobileId;
    }

    public String getUrl() {
        return url;
    }
}
