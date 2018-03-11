package mobile.com.mobilephonebuyers.favorite.adapter.view;

import java.util.List;

import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;

/**
 * Created by bill on 11/3/2018 AD.
 */

public interface IFavoriteListAdapterView {
    void updateViewMobileFavorite(List<MobileObject> mobileList);

    void updateViewRemoveFavoriteSuccessful(int position);

    void updateViewRemoveFavoriteNotSuccessful(int position);

    interface FavoriteListAdapterViewListener {
        void onClickMobileDetailListener(MobileObject mobileDetail);
    }
}
