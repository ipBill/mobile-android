package mobile.com.mobilephonebuyers.realm;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;

/**
 * Created by bill on 11/3/2018 AD.
 */

public class MobileRealmObject extends RealmObject {

    @PrimaryKey
    int id;
    String thumbImageURL;
    String description;
    String brand;
    float price;
    float rating;
    String name;
    boolean isFavorite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThumbImageURL() {
        return thumbImageURL;
    }

    public void setThumbImageURL(String thumbImageURL) {
        this.thumbImageURL = thumbImageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
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
