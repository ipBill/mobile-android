package mobile.com.mobilephonebuyers.manager;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;
import mobile.com.mobilephonebuyers.realm.MobileRealmObject;

/**
 * Created by bill on 11/3/2018 AD.
 */

public class RealmManager {

    private Realm realm;

    private static final RealmManager ourInstance = new RealmManager();

    public static RealmManager getInstance() {
        return ourInstance;
    }

    private RealmManager() {
        realm = Realm.getDefaultInstance();
    }

    public void insertOrUpdateMobileList(List<MobileObject> dao) {
        try {
            realm.beginTransaction();
            for (MobileObject mobileObject : dao) {

                //Find first
                MobileRealmObject findDouble = realm.where(MobileRealmObject.class)
                        .equalTo("id", mobileObject.getId())
                        .findFirst();

                if (findDouble == null) {

                    MobileRealmObject realmObject = new MobileRealmObject();
                    realmObject.setId(mobileObject.getId());
                    realmObject.setName(mobileObject.getName());
                    realmObject.setDescription(mobileObject.getDescription());
                    realmObject.setBrand(mobileObject.getBrand());
                    realmObject.setPrice(mobileObject.getPrice());
                    realmObject.setRating(mobileObject.getRating());
                    realmObject.setThumbImageURL(mobileObject.getThumbImageURL());
                    realmObject.setFavorite(false);
                    realm.insertOrUpdate(realmObject);

                }
            }
            realm.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<MobileObject> loadMobileListFromLocal(int sortId) {
        RealmResults<MobileRealmObject> realmResult;
        List<MobileObject> result = new ArrayList<>();
        if (sortId == 0) { //Low to High
            realmResult = realm.where(MobileRealmObject.class).sort("price", Sort.ASCENDING).findAll();
        } else if (sortId == 1) { //High to Low
            realmResult = realm.where(MobileRealmObject.class).sort("price", Sort.DESCENDING).findAll();
        } else if (sortId == 2) { //Rating 5-1
            realmResult = realm.where(MobileRealmObject.class).sort("rating", Sort.DESCENDING).findAll();
        } else {
            realmResult = realm.where(MobileRealmObject.class).sort("price", Sort.ASCENDING).findAll();
        }
        for (MobileRealmObject realmObject : realmResult) {
            MobileObject mobileObject = new MobileObject();
            mobileObject.setId(realmObject.getId());
            mobileObject.setName(realmObject.getName());
            mobileObject.setDescription(realmObject.getDescription());
            mobileObject.setBrand(realmObject.getBrand());
            mobileObject.setPrice(realmObject.getPrice());
            mobileObject.setRating(realmObject.getRating());
            mobileObject.setThumbImageURL(realmObject.getThumbImageURL());
            mobileObject.setFavorite(realmObject.isFavorite());
            result.add(mobileObject);
        }
        return result;
    }

    public boolean updateFavoriteToLocal(MobileObject mobileObject, boolean isFavorite) {
        boolean isSuccess;
        try {
            MobileRealmObject realmObject = new MobileRealmObject();
            realmObject.setId(mobileObject.getId());
            realmObject.setName(mobileObject.getName());
            realmObject.setDescription(mobileObject.getDescription());
            realmObject.setBrand(mobileObject.getBrand());
            realmObject.setPrice(mobileObject.getPrice());
            realmObject.setRating(mobileObject.getRating());
            realmObject.setThumbImageURL(mobileObject.getThumbImageURL());
            realmObject.setFavorite(isFavorite);

            realm.beginTransaction();
            realm.copyToRealmOrUpdate(realmObject);
            realm.commitTransaction();

            isSuccess = true;

        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }
        return isSuccess;
    }

    public List<MobileObject> loadMobileListFavoriteFromLocal(boolean isFavorite, int sortId) {
        List<MobileObject> result = new ArrayList<>();
        RealmResults<MobileRealmObject> realmResult;

        if (sortId == 0) { //Low to High
            realmResult = realm.where(MobileRealmObject.class)
                    .equalTo("isFavorite", isFavorite)
                    .sort("price", Sort.ASCENDING).findAll();
        } else if (sortId == 1) { //High to Low
            realmResult = realm.where(MobileRealmObject.class)
                    .equalTo("isFavorite", isFavorite)
                    .sort("price", Sort.DESCENDING).findAll();
        } else if (sortId == 2) { //Rating 5-1
            realmResult = realm.where(MobileRealmObject.class)
                    .equalTo("isFavorite", isFavorite)
                    .sort("rating", Sort.DESCENDING).findAll();
        } else {
            realmResult = realm.where(MobileRealmObject.class)
                    .equalTo("isFavorite", isFavorite)
                    .sort("price", Sort.ASCENDING).findAll();
        }

        for (MobileRealmObject realmObject : realmResult) {
            MobileObject mobileObject = new MobileObject();
            mobileObject.setId(realmObject.getId());
            mobileObject.setName(realmObject.getName());
            mobileObject.setDescription(realmObject.getDescription());
            mobileObject.setBrand(realmObject.getBrand());
            mobileObject.setPrice(realmObject.getPrice());
            mobileObject.setRating(realmObject.getRating());
            mobileObject.setThumbImageURL(realmObject.getThumbImageURL());
            mobileObject.setFavorite(realmObject.isFavorite());
            result.add(mobileObject);
        }
        return result;
    }

}
