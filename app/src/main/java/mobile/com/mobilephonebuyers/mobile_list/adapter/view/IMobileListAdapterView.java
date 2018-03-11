package mobile.com.mobilephonebuyers.mobile_list.adapter.view;

import java.util.List;

import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;

/**
 * Created by bill on 11/3/2018 AD.
 */

public interface IMobileListAdapterView {
    void updateViewMobileList(List<MobileObject> body);

    void updateViewForFavorite(List<MobileObject> mobileObjects);

    void canNotUpdateFavorite();

    interface MobileListAdapterListener {
        void onClickFavoritePressed(MobileObject mobileObject, boolean isFavorite);

        void canNotUpdateFavorite();

        void canUpdateFavoriteSuccessful();
    }
}
