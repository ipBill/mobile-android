package mobile.com.mobilephonebuyers.mobile_list.dao;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by bill on 11/3/2018 AD.
 */

@Parcel
public class MobileObject {

    @SerializedName("id")
    int id;

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

    @SerializedName("name")
    String name;

    boolean isFavorite;

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

    public void setId(int id) {
        this.id = id;
    }

    public void setThumbImageURL(String thumbImageURL) {
        this.thumbImageURL = thumbImageURL;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
