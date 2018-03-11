package mobile.com.mobilephonebuyers.mobile_list.dao;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by bill on 11/3/2018 AD.
 */

@Parcel
public class MobileObject {

    @SerializedName("thumbImageURL")
    String thumbImageURL;

    @SerializedName("description")
    String description;

    @SerializedName("brand")
    String brand;

    @SerializedName("price")
    float price;

    @SerializedName("rating")
    float rating;

    @SerializedName("id")
    int id;

    @SerializedName("name")
    String name;

    public String getThumbImageURL() {
        return thumbImageURL;
    }

    public String getDescription() {
        return description;
    }

    public String getBrand() {
        return brand;
    }

    public float getPrice() {
        return price;
    }

    public float getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
