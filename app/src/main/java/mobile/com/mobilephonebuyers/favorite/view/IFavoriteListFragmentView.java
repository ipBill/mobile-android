package mobile.com.mobilephonebuyers.favorite.view;

import java.util.List;

import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;

/**
 * Created by bill on 11/3/2018 AD.
 */

public interface IFavoriteListFragmentView {
    void updateViewMobileFavorite(List<MobileObject> mobileList);

    void updateViewRemoveFavoriteSuccessful(int position);

    void updateViewRemoveFavoriteNotSuccessful(int position);

    void updateViewMobileFavoriteEmpty(List<MobileObject> mobileList);

    interface FavoriteListFragmentListener {
        void onUpdateMobileListListener();

        void onClickMobileDetailListener(MobileObject mobileDetail);
    }
}
